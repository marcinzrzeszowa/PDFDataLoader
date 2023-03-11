package org.example.structure;

import java.io.IOException;
import java.util.ArrayList;

public interface MeasurementReportMethods {
    ReportHeader extractHeader(StringBuilder rawInputText) throws IOException;
    ArrayList<ReportAttribute> extractAttributes(StringBuilder rawInputText) throws IOException;
}
