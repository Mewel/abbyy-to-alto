/*
  Copyright (c) 2016 Matthias Eichner
  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
  documentation files (the "Software"), to deal in the Software without restriction, including without
  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
  conditions:

  The above copyright notice and this permission notice shall be included in all copies or substantial
  portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
  TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
  DEALINGS IN THE SOFTWARE.
*/
package org.mycore.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.alto.v2.Alto;
import org.mycore.xml.alto.v4.AltoType;
import utils.stream.Unthrow;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 * A very simple CLI to convert single abbyy files or whole directories.
 * <p>
 * <ul>
 * <li>convert {source abbyy file} {target alto diretory}</li>
 * <li>directory {source abbyy directory} {target alto diretory}</li>
 * </ul>
 *
 * @author Matthias Eichner
 */
public class MultiConverterCLI {
    private enum AltoVersion {
        V2,
        V4
    }

    private static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            LOGGER.info("Please specify the input and the output directory:");
            LOGGER.info("  MultiConverterCLI convert[4] {source abbyy file} {target directory}");
            LOGGER.info("  MultiConverterCLI directory[4] {source directory} {target directory}");
            LOGGER.info("  MultiConverterCLI matching[4] {source directory} {matching folder name} {target folder name}");
            return;
        }

        String command = args[0];
        switch (command) {
            case "convert":
                LOGGER.info("Start converting abbyy file...");
                convert(AltoVersion.V2, Paths.get(args[1]), Paths.get(args[2]));
                LOGGER.info("Converting done");
                break;

            case "convert4":
                LOGGER.info("Start converting abbyy file to ALTO v4...");
                convert(AltoVersion.V4, Paths.get(args[1]), Paths.get(args[2]));
                LOGGER.info("Converting done");
                break;

            case "directory":
                LOGGER.info("Start converting abbyy files...");
                convertDirectory(AltoVersion.V2, Paths.get(args[1]), Paths.get(args[2]));
                break;

            case "directory4":
                LOGGER.info("Start converting abbyy files to ALTO v4...");
                convertDirectory(AltoVersion.V4, Paths.get(args[1]), Paths.get(args[2]));
                break;

            case "matching":
                convertMatching(AltoVersion.V2, Paths.get(args[1]), args[2], args[3]);
                break;

            case "matching4":
                convertMatching(AltoVersion.V4, Paths.get(args[1]), args[2], args[3]);
                break;
                
            default:
                LOGGER.info("Unknown command " + command);
                break;
        }
    }

    /**
     * Runs through all sub directories of sourceDirectoryPath and tries to find
     * matching folders. When a folders matches (the name is equal the matching
     * folder variable), all *.xml files of this folder will be converted
     * and stored under the new folder.
     *
     * @param version             Alto schema version to use
     * @param sourceDirectoryPath path where to start converting
     * @param matchingFolder      folder name where the *.xml files lies within
     * @param newFolder           name of the folder which will be created for converted *.xml
     *                            The folder is always a sibling of the matching folder
     * @throws IOException some storing went wrong
     */
    private static void convertMatching(AltoVersion version, Path sourceDirectoryPath, String matchingFolder, String newFolder)
            throws IOException {
        try (Stream<Path> stream = Files.walk(sourceDirectoryPath)) {
            stream.filter(p -> {
                return Files.isDirectory(p) && Files.exists(p.resolve(matchingFolder));
            }).flatMap(baseDirectory -> {
                return Unthrow.wrap(() -> Files.list(baseDirectory.resolve(matchingFolder)));
            }).forEach(sourcePath -> {
                Unthrow.wrapProc(() -> convert(version, sourcePath, sourcePath.getParent().getParent().resolve(newFolder)));
            });
        }
    }

    /**
     * Converts a whole directory of abbyy xml files and stores them under the given
     * target directory path. The names of the alto files are the same as the abbyy ones.
     *
     * @param version             Alto schema version to use
     * @param sourceDirectoryPath a directory path containing abbyy xml files
     * @param targetDirectoryPath where the alto files are stored
     * @throws IOException some storing went wrong
     */
    private static void convertDirectory(AltoVersion version, Path sourceDirectoryPath, Path targetDirectoryPath) throws IOException {
        LOGGER.info("Source directory: " + sourceDirectoryPath);
        LOGGER.info("Target directory: " + targetDirectoryPath);
        Files.list(sourceDirectoryPath).filter(path -> {
            return path.toString().endsWith(".xml");
        }).forEach(sourcePath -> {
            Unthrow.wrapProc(() -> convert(version, sourcePath, targetDirectoryPath));
        });
    }

    /**
     * Converts a single abbyy file to an alto one and stores it under the target directoy path.
     * The name of the alto file is the same as the abbyy one.
     *
     * @param version             Alto schema version to use
     * @param abbyyFilePath       path to the abbyy xml file
     * @param targetDirectoryPath path to the target directory
     * @throws IOException   some storing went wrong
     * @throws JAXBException the converting went wrong
     */
    private static void convert(AltoVersion version, Path abbyyFilePath, Path targetDirectoryPath) throws IOException, JAXBException {
        LOGGER.info("Convert " + abbyyFilePath + " to " + targetDirectoryPath + " (" + version + ")");

        // read abbyy xml
        Document abbyyDocument;
        try (InputStream inputStream = Files.newInputStream(abbyyFilePath, StandardOpenOption.READ)) {
            abbyyDocument = JAXBUtil.unmarshalAbbyyDocument(inputStream);
        }

        // create output file
        if (!Files.exists(targetDirectoryPath)) {
            Files.createDirectories(targetDirectoryPath);
        }
        Path outputFile = targetDirectoryPath.resolve(abbyyFilePath.getFileName());

        // convert
        if (version == AltoVersion.V2) {
            AbbyyToAltoConverter converter = new AbbyyToAltoConverter();
            converter.setDefaultFontFamily("Arial");
            converter.setDefaultFontSize(10.0f);

            Alto alto = converter.convert(abbyyDocument);
            try (OutputStream outStream = Files.newOutputStream(outputFile)) {
                JAXBUtil.marshalAlto(alto, outStream);
            }

        } else if (version == AltoVersion.V4) {
            AbbyyToAltoV4Converter converter = new AbbyyToAltoV4Converter();
            converter.setDefaultFontFamily("Arial");
            converter.setDefaultFontSize(10.0f);
            converter.setEnableConfidence(true);

            AltoType alto = converter.convert(abbyyDocument);
            try (OutputStream outStream = Files.newOutputStream(outputFile)) {
                JAXBUtil.marshalAltoV4(alto, outStream);
            }

        } else {
            throw new RuntimeException("Invalid Alto output version specified");
        }
    }
}
