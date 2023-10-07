package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.Try;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class WindowTabData extends WebServiceResponse{
    public final static String JSON_KEY = "WindowTabData";
    private final int numberOfRows;
    private final int totalRows;
    private final int startRow;
    private final int rowCount;
    private final boolean isSucceed;
    private final DataSet dataSet;
    public WindowTabData(JSONObject jsonWindowTabData) throws WebServiceResponseException {
        super(jsonWindowTabData);

        try {
            numberOfRows = jsonWindowTabData.getInt("@NumRows");
            totalRows = jsonWindowTabData.getInt("@TotalRows");
            startRow = jsonWindowTabData.getInt("@StartRow");
            rowCount = jsonWindowTabData.getInt("RowCount");
            isSucceed = jsonWindowTabData.getBoolean("Success");

            dataSet = Optional.of(jsonWindowTabData)
                    .map(Try.onFunction(json -> json.getJSONObject(DataSet.JSON_KEY), (JSONException ignored) -> null))
                    .map(DataSet::new)
                    .orElseGet(DataSet::new);
        } catch (JSONException e) {
            throw new WebServiceResponseException(e);
        }
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getRowCount() {
        return rowCount;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public DataRow[] getDataRows() {
        return dataSet.getDataRows();
    }
}
