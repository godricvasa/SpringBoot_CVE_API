package com.Secure.Secure.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "CveCollection")
@JsonPropertyOrder({
        "id", "sourceIdentifier", "published", "lastModified", "vulnStatus",
        "cveTags", "descriptions", "metrics", "weaknesses", "configurations", "references"
})
@Data
@NoArgsConstructor
public class Model {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("sourceIdentifier")
    private String sourceIdentifier;

    @JsonProperty("published")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date published;

    @JsonProperty("lastModified")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date lastModified;

    @JsonProperty("vulnStatus")
    private String vulnStatus;

    @JsonProperty("cveTags")
    private List<CveTag> cveTags;

    @JsonProperty("descriptions")
    private List<Description> descriptions;

    @JsonProperty("metrics")
    private Metrics metrics;

    @JsonProperty("weaknesses")
    private List<Weakness> weaknesses;

    @JsonProperty("configurations")
    private List<Configuration> configurations;

    @JsonProperty("references")
    private List<Reference> references;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class CveTag {
        @JsonProperty("type")
        private String type;

        @JsonProperty("value")
        private String value;

    }

    @Data
    @NoArgsConstructor
    public static class Description {
        @JsonProperty("lang")
        private String lang;

        @JsonProperty("value")
        private String value;
    }

    @Data
    @NoArgsConstructor
    public static class Metrics {
        @JsonProperty("cvssMetricV2")
        private List<CvssMetricV2> cvssMetricV2;

        @Data
        @NoArgsConstructor
        public static class CvssMetricV2 {
            @JsonProperty("source")
            private String source;

            @JsonProperty("type")
            private String type;

            @JsonProperty("cvssData")
            private CvssData cvssData;

            @JsonProperty("baseSeverity")
            private String baseSeverity;

            @JsonProperty("exploitabilityScore")
            private Double exploitabilityScore;

            @JsonProperty("impactScore")
            private Double impactScore;

            @JsonProperty("acInsufInfo")
            private Boolean acInsufInfo;

            @JsonProperty("obtainAllPrivilege")
            private Boolean obtainAllPrivilege;

            @JsonProperty("obtainUserPrivilege")
            private Boolean obtainUserPrivilege;

            @JsonProperty("obtainOtherPrivilege")
            private Boolean obtainOtherPrivilege;

            @JsonProperty("userInteractionRequired")
            private Boolean userInteractionRequired;

            @Data
            @NoArgsConstructor
            public static class CvssData {
                @JsonProperty("version")
                private String version;

                @JsonProperty("vectorString")
                private String vectorString;

                @JsonProperty("accessVector")
                private String accessVector;

                @JsonProperty("accessComplexity")
                private String accessComplexity;

                @JsonProperty("authentication")
                private String authentication;

                @JsonProperty("confidentialityImpact")
                private String confidentialityImpact;

                @JsonProperty("integrityImpact")
                private String integrityImpact;

                @JsonProperty("availabilityImpact")
                private String availabilityImpact;

                @JsonProperty("baseScore")
                private Double baseScore;
            }
        }
    }

    @Data
    @NoArgsConstructor
    public static class Weakness {
        @JsonProperty("source")
        private String source;

        @JsonProperty("type")
        private String type;

        @JsonProperty("description")
        private List<WeaknessDescription> description;

        @Data
        @NoArgsConstructor
        public static class WeaknessDescription {
            @JsonProperty("lang")
            private String lang;

            @JsonProperty("value")
            private String value;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Configuration {
        @JsonProperty("nodes")
        private List<Node> nodes;

        @Data
        @NoArgsConstructor
        public static class Node {
            @JsonProperty("operator")
            private String operator;

            @JsonProperty("negate")
            private Boolean negate;

            @JsonProperty("cpeMatch")
            private List<CpeMatch> cpeMatch;

            @Data
            @NoArgsConstructor
            public static class CpeMatch {
                @JsonProperty("vulnerable")
                private Boolean vulnerable;

                @JsonProperty("criteria")
                private String criteria;

                @JsonProperty("matchCriteriaId")
                private String matchCriteriaId;
            }
        }
    }

    @Data
    @NoArgsConstructor
    public static class Reference {
        @JsonProperty("url")
        private String url;

        @JsonProperty("source")
        private String source;
    }
}
