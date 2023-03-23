package org.example.structure;

import java.util.ArrayList;

public class Return {
    private String message;
    private boolean valid;
    private int intValue;
    private String strValue;
    private ArrayList<String> strArrayList;

    private Return(ResultBuilder builder) {
        this.message = builder.message;
        this.valid = builder.valid;
        this.intValue = builder.intValue;
        this.strValue = builder.strValue;
        this.strArrayList = builder.strArrayList;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public ArrayList<String> getStrArrayList() {
        return strArrayList;
    }

    public static class ResultBuilder {
        private String message;
        private boolean valid;
        private int intValue;
        private String strValue;
        public ArrayList<String> strArrayList;

        public ResultBuilder(String message, boolean valid) {
            String messageTrue = "Valid Result";
            String messageFalse = "No valid Result";

            if (message.isBlank()) {
                message = valid ? messageTrue : messageFalse;
            }
            this.message = message;
            this.valid = valid;
        }

        public ResultBuilder(boolean valid) {
            this("", valid);
        }

        public ResultBuilder addInt(int intValue) {
            this.intValue = intValue;
            return this;
        }

        public ResultBuilder addStr(String stringValue) {
            this.strValue = stringValue;
            return this;
        }

        public ResultBuilder addArrayList(ArrayList<String> objectsArrayList) {
            this.strValue = strValue;
            return this;
        }

        public Return build() {
            return new Return(this);
        }
    }

}
