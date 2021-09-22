package org.gbif.pipelines.core.parsers.clustering;

import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.APPROXIMATE_DATE;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.IDENTIFIERS_OVERLAP;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.SAME_ACCEPTED_SPECIES;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.SAME_COUNTRY;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.SAME_DATE;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.SAME_RECORDER_NAME;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.SAME_SPECIMEN;
import static org.gbif.pipelines.core.parsers.clustering.RelationshipAssertion.FeatureAssertion.WITHIN_200m;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/** Tests for relationship assertions using simple POJOs as the source. */
public class OccurrenceRelationshipsTest {
  @Test
  public void testSimpleAssertions() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("o1")
            .datasetKey("1")
            .occurrenceID("1")
            .speciesKey("1")
            .decimalLatitude(44.0d)
            .decimalLongitude(44.0d)
            .catalogNumber("TIM1")
            .year(1978)
            .month(12)
            .day(21)
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("o2")
            .datasetKey("2")
            .occurrenceID("2")
            .speciesKey("1")
            .decimalLatitude(44.0d)
            .decimalLongitude(44.0d)
            .catalogNumber("TIM1")
            .year(1978)
            .month(12)
            .day(21)
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);

    assertNotNull(assertion);
    assertTrue(assertion.justificationContains(SAME_ACCEPTED_SPECIES));
  }

  /** Real data from records 2332470913, 2571156410 which should cluster. */
  @Test
  public void testCortinarius() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("o1")
            .datasetKey("1")
            .occurrenceID("urn:catalog:O:F:304835")
            .recordNumber("TEB 12-16")
            .speciesKey("3348943")
            .decimalLatitude(60.3302d)
            .decimalLongitude(10.4647d)
            .catalogNumber("304835")
            .year(2016)
            .month(6)
            .day(11)
            .eventDate("2016-06-11T00:00:00")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("o2")
            .datasetKey("2")
            .occurrenceID("urn:uuid:152ce614-69e1-4fbe-8f1c-3340d0a15491")
            .speciesKey("3348943")
            .decimalLatitude(60.330181d)
            .decimalLongitude(10.464743d)
            .catalogNumber("O-DFL-6644/2-D")
            .recordNumber("TEB 12-16")
            .year(2016)
            .month(6)
            .day(11)
            .eventDate("2016-06-11T00:00:00")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);

    assertNotNull(assertion);
    assertTrue(assertion.justificationContains(SAME_ACCEPTED_SPECIES));
  }

  // Test even with nonsense a Holotype of the same name must be the same specimen (or worth
  // investigating a data issue)
  @Test
  public void testHolotype() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("o1")
            .datasetKey("1")
            .taxonKey("3350984")
            .decimalLatitude(10d)
            .decimalLongitude(10d)
            .countryCode("DK")
            .typeStatus("HoloType")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("o2")
            .datasetKey("2")
            .taxonKey("3350984")
            .decimalLatitude(20d) // different
            .decimalLongitude(20d) // different
            .countryCode("NO") // different
            .typeStatus("HoloType")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContains(SAME_SPECIMEN));
  }

  // Test that two records with same collector, approximate location but a day apart match.
  // https://github.com/gbif/occurrence/issues/177
  @Test
  public void testDayApart() {
    // real records where a trap set one evening and visited the next day is shared twice using
    // different
    // days
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("49635968")
            .datasetKey("1")
            .speciesKey("1850114")
            .decimalLatitude(55.737d)
            .decimalLongitude(12.538d)
            .year(2004)
            .month(8)
            .day(1) // day trap set
            .countryCode("DK")
            .recordedBy("Donald Hobern")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("1227719129")
            .datasetKey("2")
            .speciesKey("1850114")
            .decimalLatitude(55.736932d) // different
            .decimalLongitude(12.538104d)
            .year(2004)
            .month(8)
            .day(2) // day collected
            .countryCode("DK")
            .recordedBy("Donald Hobern")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(
        assertion.justificationContainsAll(
            APPROXIMATE_DATE, WITHIN_200m, SAME_COUNTRY, SAME_RECORDER_NAME));
  }

  // test 3 decimal place rounding example clusters
  @Test
  public void test3DP() {
    // real records of Seigler & Miller
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("1675790844")
            .datasetKey("1")
            .speciesKey("3794925")
            .decimalLatitude(21.8656d)
            .decimalLongitude(-102.909d)
            .year(2007)
            .month(5)
            .day(26)
            .recordedBy("D. S. Seigler & J. T. Miller")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("2268858676")
            .datasetKey("2")
            .speciesKey("3794925")
            .decimalLatitude(21.86558d)
            .decimalLongitude(-102.90929d)
            .year(2007)
            .month(5)
            .day(26)
            .recordedBy("David S. Seigler|J.T. Miller") // we should at some point detect this match
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContainsAll(SAME_DATE, WITHIN_200m, SAME_ACCEPTED_SPECIES));
  }

  // test that triplets used in e.g. catalogNumber will be handled (e.g. arctos, embl)
  @Test
  public void testMaterialiseTriplet() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("1")
            .datasetKey("1")
            .speciesKey("1")
            .institutionCode("A")
            .collectionCode("B")
            .catalogNumber("C")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("2")
            .datasetKey("2")
            .speciesKey("1")
            .eventDate("20210101")
            .decimalLatitude(1.0)
            .decimalLongitude(1.0)
            .catalogNumber("A:B:C")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContainsAll(IDENTIFIERS_OVERLAP));
  }

  // Test that a single otherCatalogNumber matches with catalogNumbers
  @Test
  public void testOtherCatalogNumber() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("1")
            .datasetKey("1")
            .speciesKey("1")
            .institutionCode("A")
            .collectionCode("B")
            .catalogNumber("C")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("2")
            .datasetKey("2")
            .speciesKey("1")
            .eventDate("20210101")
            .decimalLatitude(1.0)
            .decimalLongitude(1.0)
            .otherCatalogNumbers("A:B:C")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContainsAll(IDENTIFIERS_OVERLAP));
  }

  // Test that multiple otherCatalogNumbers match with catalogNumbers
  @Test
  public void testMultipleOtherCatalogNumbers() {
    OccurrenceFeatures o1 =
        OccurrenceFeaturesPojo.builder()
            .id("1")
            .datasetKey("1")
            .speciesKey("1")
            .institutionCode("A")
            .collectionCode("B")
            .catalogNumber("C")
            .build();

    OccurrenceFeatures o2 =
        OccurrenceFeaturesPojo.builder()
            .id("2")
            .datasetKey("2")
            .speciesKey("1")
            .eventDate("20210101")
            .decimalLatitude(1.0)
            .decimalLongitude(1.0)
            .otherCatalogNumbers("A:B:C|Something")
            .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContainsAll(IDENTIFIERS_OVERLAP));
  }

  // Test match between otherCatalogNumbers with a space in the delimiter and fieldNumber identifiers
  @Test
  public void testOtherCatalogNumbersWithSpaces() {
    OccurrenceFeatures o1 =
            OccurrenceFeaturesPojo.builder()
                    .id("1")
                    .datasetKey("1")
                    .speciesKey("1")
                    .fieldNumber("AMD0000145")
                    .build();

    OccurrenceFeatures o2 =
            OccurrenceFeaturesPojo.builder()
                    .id("2")
                    .datasetKey("2")
                    .speciesKey("1")
                    .eventDate("20210101")
                    .decimalLatitude(1.0)
                    .decimalLongitude(1.0)
                    .otherCatalogNumbers("AMD0000145 | 092026")
                    .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNotNull(assertion);
    assertTrue(assertion.justificationContainsAll(IDENTIFIERS_OVERLAP));
  }

  // Test that otherCatalogNumbers which do not match with catalogNumbers do not trigger a justification
  @Test
  public void testNonMatchingOtherCatalogNumbers() {
    OccurrenceFeatures o1 =
            OccurrenceFeaturesPojo.builder()
                    .id("1")
                    .datasetKey("1")
                    .speciesKey("1")
                    .institutionCode("A")
                    .collectionCode("B")
                    .catalogNumber("C")
                    .build();

    OccurrenceFeatures o2 =
            OccurrenceFeaturesPojo.builder()
                    .id("2")
                    .datasetKey("2")
                    .speciesKey("1")
                    .eventDate("20210101")
                    .decimalLatitude(1.0)
                    .decimalLongitude(1.0)
                    .otherCatalogNumbers("A:B:D|Something")
                    .build();

    RelationshipAssertion<OccurrenceFeatures> assertion = OccurrenceRelationships.generate(o1, o2);
    assertNull(assertion);
  }

  @Test
  public void testNormalizeID() {
    assertEquals("ABC", OccurrenceRelationships.normalizeID(" A-/, B \\C"));
    // These are examples of collectors we could be able to organize in the future
    assertEquals(
        "DAVIDSSEIGLERJTMILLER",
        OccurrenceRelationships.normalizeID("David S. Seigler|J.T. Miller"));
    assertEquals(
        "DSSEIGLERJTMILLER", OccurrenceRelationships.normalizeID("D. S. Seigler & J. T. Miller"));
  }

  @Test
  public void allNull() {
    assertTrue(OccurrenceRelationships.allNull(null, null));
    assertTrue(OccurrenceRelationships.allNull(null));
    assertFalse(OccurrenceRelationships.allNull(null, ""));
  }
}
