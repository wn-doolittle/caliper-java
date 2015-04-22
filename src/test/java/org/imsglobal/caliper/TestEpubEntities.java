package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;

public class TestEpubEntities {

    /**
     * Constructor
     */
    public TestEpubEntities() {

    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubVolume buildEpubVolume43() {
        return EpubVolume.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
            .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
            .version("2nd ed.")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap431() {
        DigitalResource parent = buildEpubVolume43();
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/1)")
            .name("Key Figures: George Washington")
            .isPartOf(parent)
            .version(parent.getVersion())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap432() {
        DigitalResource parent = buildEpubVolume43();
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/2)")
            .name("Key Figures: Lord North")
            .isPartOf(parent)
            .version(parent.getVersion())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap433() {
        DigitalResource parent = buildEpubVolume43();
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/3)")
            .name("Key Figures: John Adams")
            .isPartOf(parent)
            .version(parent.getVersion())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap434() {
        DigitalResource parent = buildEpubVolume43();
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/4)")
            .name("The Stamp Act Crisis")
            .isPartOf(parent)
            .version(parent.getVersion())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }
}