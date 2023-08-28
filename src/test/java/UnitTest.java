import com.kinnarastudio.idempiere.exception.WebServiceBuilderException;
import com.kinnarastudio.idempiere.exception.WebServiceRequestException;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import com.kinnarastudio.idempiere.model.WebServiceResponse;
import com.kinnarastudio.idempiere.type.ModelOrientedWebServiceMethod;
import com.kinnarastudio.idempiere.model.LoginRequest;
import com.kinnarastudio.idempiere.webservice.ModelOrientedWebService;
import org.junit.Test;

import java.io.IOException;

public class UnitTest {
    @Test
    public void readData() {
        try {
            ModelOrientedWebService webService = new ModelOrientedWebService.Builder()
                    .setHost("localhost")
                    .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11,103))
                    .setServiceType("ReadBPartner")
                    .setRecordId(1000004)
                    .setMethod(ModelOrientedWebServiceMethod.READ_DATA)
                    .setTable("M_InOut")
                    .ignoreSslCertificateError()
                    .build();

            WebServiceResponse response = webService.execute();
            System.out.println(response.toString());
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void queryData() {
        try {
            ModelOrientedWebService webService = new ModelOrientedWebService.Builder()
                    .setBaseUrl("https://localhost:8443")
                    .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11,103))
                    .setServiceType("QueryUom")
                    .setMethod(ModelOrientedWebServiceMethod.QUERY_DATA)
                    .ignoreSslCertificateError()
                    .build();

            WebServiceResponse response = webService.execute();
            System.out.println(response.toString());
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }
}
