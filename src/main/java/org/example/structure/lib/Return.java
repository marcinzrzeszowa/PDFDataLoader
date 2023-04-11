package org.example.structure.lib;

import java.util.ArrayList;

public class Return {
    private String message;
    private boolean valid;
    private int intValue;
    private String strValue;
    private StringBuilder strBValue;
    private ArrayList<String> strArrayList;
    private ArrayList<Integer> intArrayList;

    private Return(ReturnBuilder builder) {
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
    public StringBuilder getStrBValue() {
        return strBValue;
    }
    public ArrayList<Integer> getIntArrayList() {
        return intArrayList;
    }


    public static class ReturnBuilder {
        private String message;
        private boolean valid;
        private int intValue;
        private String strValue;
        private StringBuilder strBValue;
        private ArrayList<String> strArrayList;
        private ArrayList<Integer> intArrayList;

        public ReturnBuilder(String message, boolean valid) {
            String messageTrue = "Valid Result";
            String messageFalse = "No valid Result";

            if (message.isBlank()) {
                message = valid ? messageTrue : messageFalse;
            }
            this.message = message;
            this.valid = valid;
        }

        public ReturnBuilder(boolean valid) {
            this("", valid);
        }

        public ReturnBuilder addInt(int intValue) {
            this.intValue = intValue;
            return this;
        }

        public ReturnBuilder addStr(String stringValue) {
            this.strValue = stringValue;
            return this;
        }

        public ReturnBuilder addStrB(StringBuilder strBValue) {
            this.strBValue = strBValue;
            return this;
        }

        public ReturnBuilder addStrArrayList(ArrayList<String> objectsArrayList) {
            this.strArrayList = objectsArrayList;
            return this;
        }

        public ReturnBuilder addIntArrayList(ArrayList<Integer> objectsArrayList) {
            this.intArrayList = objectsArrayList;
            return this;
        }

        public Return build() {
            return new Return(this);
        }
    }

}
