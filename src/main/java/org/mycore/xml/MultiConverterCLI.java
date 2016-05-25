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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.alto.v2.Alto;

import utils.stream.Unthrow;

/**
 * A very simple CLI to convert single abbyy files or whole directories.
 * 
 * <ul>
 *   <li>convert {source abbyy file} {target alto diretory}</li>
 *   <li>directory {source abbyy directory} {target alto diretory}</li>
 * </ul>
 * 
 * @author Matthias Eichner
 */
public class MultiConverterCLI {

    private static Logger LOGGER = LogManager.getLogger(MultiConverterCLI.class);

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            LOGGER.info("Please specify the input and the output directory:");
            LOGGER.info("  MultiConverterCLI convert {source abbyy file} {target directory}");
            LOGGER.info("  MultiConverterCLI directory {source directory} {target directory}");
            LOGGER.info("  MultiConverterCLI jvb {source directory}");
            return;
        }

        String command = args[0];
        if (command.equals("convert")) {
            LOGGER.info("Start converting abbyy file...");
            convert(Paths.get(args[1]), Paths.get(args[2]));
            LOGGER.info("Converting done");
        } else if (command.equals("directory")) {
            LOGGER.info("Start converting abbyy files...");
            convertDirectory(Paths.get(args[1]), Paths.get(args[2]));
        } else if (command.equals("jvb")) {
            convertJVB(args[1]);
        } else {
            LOGGER.info("Unknown command " + command);
        }
    }

    /**
     * jvb /data/temp/mnt/images/OCRbearbInnsbruck_2/1914
     * 
     * @param sourceDirectory
     * @throws IOException
     */
    private static void convertJVB(String sourceDirectory) throws IOException {
        Files.list(Paths.get(sourceDirectory)).filter(p -> Files.isDirectory(p)).flatMap(dir -> {
            return Unthrow.wrap(() -> Files.list(dir.resolve("ocr")));
        }).forEach(sourcePath -> {
            Unthrow.wrapProc(() -> convert(sourcePath, sourcePath.getParent().getParent().resolve("mcralto")));
        });
    }

    /**
     * Converts a whole directory of abbyy xml files and stores them under the given
     * target directory path. The names of the alto files are the same as the abbyy ones.
     * 
     * @param sourceDirectoryPath a directory path containing abbyy xml files
     * @param targetDirectoryPath where the alto files are stored
     * @throws IOException some storing went wrong
     */
    private static void convertDirectory(Path sourceDirectoryPath, Path targetDirectoryPath) throws IOException {
        LOGGER.info("Source directory: " + sourceDirectoryPath);
        LOGGER.info("Target directory: " + targetDirectoryPath);
        Files.list(sourceDirectoryPath).filter(path -> {
            return path.toString().endsWith(".xml");
        }).forEach(sourcePath -> {
            Unthrow.wrapProc(() -> convert(sourcePath, targetDirectoryPath));
        });
    }

    /**
     * Converts a single abbyy file to an alto one and stores it under the target directoy path.
     * The name of the alto file is the same as the abbyy one.
     * 
     * @param abbyyFilePath path to the abbyy xml file
     * @param targetDirectoryPath path to the target directory
     * @throws IOException some storing went wrong
     * @throws JAXBException the converting went wrong
     */
    private static void convert(Path abbyyFilePath, Path targetDirectoryPath) throws IOException, JAXBException {
        LOGGER.info("Convert " + abbyyFilePath + " to " + targetDirectoryPath);

        // read abbyy xml
        Document abbyyDocument;
        try (InputStream inputStream = Files.newInputStream(abbyyFilePath, StandardOpenOption.READ)) {
            abbyyDocument = JAXBUtil.unmarshalAbbyyDocument(inputStream);
        }

        // convert
        AbbyyToAltoConverter converter = new AbbyyToAltoConverter();
        Alto alto = converter.convert(abbyyDocument);

        // create output file
        if (!Files.exists(targetDirectoryPath)) {
            Files.createDirectories(targetDirectoryPath);
        }
        Path outputFile = targetDirectoryPath.resolve(abbyyFilePath.getFileName());
        try (OutputStream outStream = Files.newOutputStream(outputFile)) {
            JAXBUtil.marshalAlto(alto, outStream);
        }
    }

}
