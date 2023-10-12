import com.kinnarastudio.idempiere.exception.WebServiceBuilderException;
import com.kinnarastudio.idempiere.exception.WebServiceRequestException;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import com.kinnarastudio.idempiere.model.*;
import com.kinnarastudio.idempiere.type.ServiceMethod;
import com.kinnarastudio.idempiere.webservice.CompositeInterfaceWebService;
import com.kinnarastudio.idempiere.webservice.ModelOrientedWebService;
import org.junit.Test;

public class UnitTest {
//    @Test
    public void readData() {
        try {
            ModelOrientedWebService webService = new ModelOrientedWebService.Builder()
                    .setHost("localhost")
                    .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11, 103))
                    .setServiceType("ReadBPartner")
                    .setRecordId(1000004)
                    .setMethod(ServiceMethod.READ_DATA)
                    .setTable("M_InOut")
                    .ignoreSslCertificateError()
                    .build();

            WebServiceResponse response = webService.execute();
            System.out.println(response.toString());
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
    public void queryData() {
        try {
            ModelOrientedWebService webService = new ModelOrientedWebService.Builder()
                    .setBaseUrl("https://localhost:8443")
                    .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11, 103))
                    .setServiceType("QueryUom")
                    .setMethod(ServiceMethod.QUERY_DATA)
                    .ignoreSslCertificateError()
                    .setLimit(2)
                    .build();

            WebServiceResponse response = webService.execute();
            System.out.println(response.toString());
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void compositeInterfaceOrderShipment() {
        CompositeInterfaceWebService.Builder builder = new CompositeInterfaceWebService.Builder();
        builder.setHost("localhost")
                .ignoreSslCertificateError()
                .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11, 103))
                .setServiceType("CompositeOrderShipment");

        DataRow orderDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("M_Warehouse_ID", 103),
                new FieldEntry("C_BPartner_ID", 1000003),
                new FieldEntry("C_BPartner_Location_ID", 50004),
                new FieldEntry("PaymentRule", "B"),
                new FieldEntry("C_DocTypeTarget_ID", 132)
        });

        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateOrderRecord", "C_Order", null, null, null, null, orderDataRow)));

        DataRow orderLineDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("C_Order_ID", "@C_Order.C_Order_ID"),
                new FieldEntry("M_Product_ID", 134),
                new FieldEntry("QtyEntered", 10),
                new FieldEntry("PriceEntered", 10000)
        });

        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateOrderLine", "C_OrderLine", null, null, null, null, orderLineDataRow)));

        DataRow shipmentDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("C_DocType_ID", 120),
                new FieldEntry("C_BPartner_ID", 1000003),
                new FieldEntry("C_BPartner_Location_ID", 50004),
                new FieldEntry("M_Warehouse_ID", 103),
                new FieldEntry("AD_User_ID", 101),
                new FieldEntry("Description", "Composite")
        });
        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateShipment", "M_InOut", null, null, null, null, shipmentDataRow)));

        DataRow shipmentLineDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("M_InOut_ID", "@M_InOut.M_InOut_ID"),
                new FieldEntry("M_Product_ID", 134),
                new FieldEntry("QtyEntered", 97),
                new FieldEntry("C_UOM_ID", 100)
        });
        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateShipmentLine", "M_InOutLine", null, null, null, null, shipmentLineDataRow)));
        try {
            CompositeInterfaceWebService webService = builder.build();
            CompositeResponses response = (CompositeResponses) webService.execute();

            if(response.isError()) {
                throw new WebServiceResponseException(String.join("\n", response.getErrorMessages()));
            }
            System.out.println(response);
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }
//    @Test
    public void compositeInterfaceShipment() {
        CompositeInterfaceWebService.Builder builder = new CompositeInterfaceWebService.Builder();
        builder.setHost("localhost")
                .ignoreSslCertificateError()
                .setLoginRequest(new LoginRequest("GardenAdmin", "GardenAdmin", "en_US", 11, 102, 11, 103))
                .setServiceType("CompositeOrderShipment");

        DataRow shipmentDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("C_DocType_ID", 120),
                new FieldEntry("C_BPartner_ID", 1000003),
                new FieldEntry("C_BPartner_Location_ID", 50004),
                new FieldEntry("M_Warehouse_ID", 103),
                new FieldEntry("AD_User_ID", 101),
                new FieldEntry("Description", "Composite")
        });
        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateShipment", "M_InOut", null, null, null, null, shipmentDataRow)));

        DataRow shipmentLineDataRow = new DataRow(new FieldEntry[]{
                new FieldEntry("M_InOut_ID", "@M_InOut.M_InOut_ID"),
                new FieldEntry("M_Product_ID", 50003),
                new FieldEntry("QtyEntered", 97),
                new FieldEntry("C_UOM_ID", 100)
        });
        builder.addOperation(new Operation(ServiceMethod.CREATE_DATA, new ModelCrud("CreateShipmentLine", "M_InOutLine", null, null, null, null, shipmentLineDataRow)));

        try {
            CompositeInterfaceWebService webService = builder.build();
            WebServiceResponse response = webService.execute();
            System.out.println(response.toString());
        } catch (WebServiceBuilderException | WebServiceRequestException | WebServiceResponseException e) {
            throw new RuntimeException(e);
        }
    }
}
