
package org.mycore.xml.alto.v4;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.mycore.xml.alto.v4 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Alto_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "alto");
    private final static QName _TagsTypeLayoutTag_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "LayoutTag");
    private final static QName _TagsTypeOtherTag_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "OtherTag");
    private final static QName _TagsTypeNamedEntityTag_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "NamedEntityTag");
    private final static QName _TagsTypeStructureTag_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "StructureTag");
    private final static QName _TagsTypeRoleTag_QNAME = new QName("http://www.loc.gov/standards/alto/ns-v4#", "RoleTag");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.mycore.xml.alto.v4
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DescriptionType }
     * 
     */
    public DescriptionType createDescriptionType() {
        return new DescriptionType();
    }

    /**
     * Create an instance of {@link TagType }
     * 
     */
    public TagType createTagType() {
        return new TagType();
    }

    /**
     * Create an instance of {@link TextBlockType }
     * 
     */
    public TextBlockType createTextBlockType() {
        return new TextBlockType();
    }

    /**
     * Create an instance of {@link TextBlockType.TextLine }
     * 
     */
    public TextBlockType.TextLine createTextBlockTypeTextLine() {
        return new TextBlockType.TextLine();
    }

    /**
     * Create an instance of {@link AltoType }
     * 
     */
    public AltoType createAltoType() {
        return new AltoType();
    }

    /**
     * Create an instance of {@link StringType }
     * 
     */
    public StringType createStringType() {
        return new StringType();
    }

    /**
     * Create an instance of {@link SPType }
     * 
     */
    public SPType createSPType() {
        return new SPType();
    }

    /**
     * Create an instance of {@link IllustrationType }
     * 
     */
    public IllustrationType createIllustrationType() {
        return new IllustrationType();
    }

    /**
     * Create an instance of {@link LayoutType }
     * 
     */
    public LayoutType createLayoutType() {
        return new LayoutType();
    }

    /**
     * Create an instance of {@link EllipseType }
     * 
     */
    public EllipseType createEllipseType() {
        return new EllipseType();
    }

    /**
     * Create an instance of {@link OcrProcessingType }
     * 
     */
    public OcrProcessingType createOcrProcessingType() {
        return new OcrProcessingType();
    }

    /**
     * Create an instance of {@link PageType }
     * 
     */
    public PageType createPageType() {
        return new PageType();
    }

    /**
     * Create an instance of {@link TextStyleType }
     * 
     */
    public TextStyleType createTextStyleType() {
        return new TextStyleType();
    }

    /**
     * Create an instance of {@link PageSpaceType }
     * 
     */
    public PageSpaceType createPageSpaceType() {
        return new PageSpaceType();
    }

    /**
     * Create an instance of {@link PolygonType }
     * 
     */
    public PolygonType createPolygonType() {
        return new PolygonType();
    }

    /**
     * Create an instance of {@link GlyphType }
     * 
     */
    public GlyphType createGlyphType() {
        return new GlyphType();
    }

    /**
     * Create an instance of {@link DocumentIdentifierType }
     * 
     */
    public DocumentIdentifierType createDocumentIdentifierType() {
        return new DocumentIdentifierType();
    }

    /**
     * Create an instance of {@link BlockType }
     * 
     */
    public BlockType createBlockType() {
        return new BlockType();
    }

    /**
     * Create an instance of {@link ParagraphStyleType }
     * 
     */
    public ParagraphStyleType createParagraphStyleType() {
        return new ParagraphStyleType();
    }

    /**
     * Create an instance of {@link ProcessingStepType }
     * 
     */
    public ProcessingStepType createProcessingStepType() {
        return new ProcessingStepType();
    }

    /**
     * Create an instance of {@link VariantType }
     * 
     */
    public VariantType createVariantType() {
        return new VariantType();
    }

    /**
     * Create an instance of {@link ALTERNATIVEType }
     * 
     */
    public ALTERNATIVEType createALTERNATIVEType() {
        return new ALTERNATIVEType();
    }

    /**
     * Create an instance of {@link TagsType }
     * 
     */
    public TagsType createTagsType() {
        return new TagsType();
    }

    /**
     * Create an instance of {@link CircleType }
     * 
     */
    public CircleType createCircleType() {
        return new CircleType();
    }

    /**
     * Create an instance of {@link SourceImageInformationType }
     * 
     */
    public SourceImageInformationType createSourceImageInformationType() {
        return new SourceImageInformationType();
    }

    /**
     * Create an instance of {@link ShapeType }
     * 
     */
    public ShapeType createShapeType() {
        return new ShapeType();
    }

    /**
     * Create an instance of {@link GraphicalElementType }
     * 
     */
    public GraphicalElementType createGraphicalElementType() {
        return new GraphicalElementType();
    }

    /**
     * Create an instance of {@link FileIdentifierType }
     * 
     */
    public FileIdentifierType createFileIdentifierType() {
        return new FileIdentifierType();
    }

    /**
     * Create an instance of {@link StylesType }
     * 
     */
    public StylesType createStylesType() {
        return new StylesType();
    }

    /**
     * Create an instance of {@link ProcessingSoftwareType }
     * 
     */
    public ProcessingSoftwareType createProcessingSoftwareType() {
        return new ProcessingSoftwareType();
    }

    /**
     * Create an instance of {@link ComposedBlockType }
     * 
     */
    public ComposedBlockType createComposedBlockType() {
        return new ComposedBlockType();
    }

    /**
     * Create an instance of {@link DescriptionType.OCRProcessing }
     * 
     */
    public DescriptionType.OCRProcessing createDescriptionTypeOCRProcessing() {
        return new DescriptionType.OCRProcessing();
    }

    /**
     * Create an instance of {@link DescriptionType.Processing }
     * 
     */
    public DescriptionType.Processing createDescriptionTypeProcessing() {
        return new DescriptionType.Processing();
    }

    /**
     * Create an instance of {@link TagType.XmlData }
     * 
     */
    public TagType.XmlData createTagTypeXmlData() {
        return new TagType.XmlData();
    }

    /**
     * Create an instance of {@link TextBlockType.TextLine.HYP }
     * 
     */
    public TextBlockType.TextLine.HYP createTextBlockTypeTextLineHYP() {
        return new TextBlockType.TextLine.HYP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "alto")
    public JAXBElement<AltoType> createAlto(AltoType value) {
        return new JAXBElement<AltoType>(_Alto_QNAME, AltoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "LayoutTag", scope = TagsType.class)
    public JAXBElement<TagType> createTagsTypeLayoutTag(TagType value) {
        return new JAXBElement<TagType>(_TagsTypeLayoutTag_QNAME, TagType.class, TagsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "OtherTag", scope = TagsType.class)
    public JAXBElement<TagType> createTagsTypeOtherTag(TagType value) {
        return new JAXBElement<TagType>(_TagsTypeOtherTag_QNAME, TagType.class, TagsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "NamedEntityTag", scope = TagsType.class)
    public JAXBElement<TagType> createTagsTypeNamedEntityTag(TagType value) {
        return new JAXBElement<TagType>(_TagsTypeNamedEntityTag_QNAME, TagType.class, TagsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "StructureTag", scope = TagsType.class)
    public JAXBElement<TagType> createTagsTypeStructureTag(TagType value) {
        return new JAXBElement<TagType>(_TagsTypeStructureTag_QNAME, TagType.class, TagsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.loc.gov/standards/alto/ns-v4#", name = "RoleTag", scope = TagsType.class)
    public JAXBElement<TagType> createTagsTypeRoleTag(TagType value) {
        return new JAXBElement<TagType>(_TagsTypeRoleTag_QNAME, TagType.class, TagsType.class, value);
    }

}
