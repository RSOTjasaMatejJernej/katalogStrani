
package si.fri.rso.katalogstrani;

import com.kumuluz.ee.common.runtime.EeRuntime;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;




@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("katalogStrani")
@ApplicationScoped
public class KatalogStraniResource {

    @Inject
    private RestProperties restProperties;

    @GET
    @Metered
    public Response getAllStranis() {
        List<Stran> strans = Database.getStrans();
        return Response.ok(strans).build();
    }

    @GET
    @Path("{stranId}")
    public Response getStran(@PathParam("stranId") Integer stranId) {
        Stran stran = Database.getStran(stranId.toString());
        return stran != null
                ? Response.ok(stran).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewStran(Stran stran) {
        Database.addStran(stran);
        return Response.ok(stran).build();
    }

    @DELETE
    @Path("{stranId}")
    public Response deleteStran(@PathParam("stranId") String stranId) {
        Database.deleteStran(stranId);
        return Response.ok(Response.Status.OK).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @POST
    @Path("load")
    public Response loadOrder(Integer n) {

        for (int i = 1; i <= n; i++) {
            fibonacci(i);
        }

        return Response.status(Response.Status.OK).build();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @GET
    @Path("instanceid")
    public Response getInstanceId() {

        String instanceId =
                "{\"instanceId\" : \"" + EeRuntime.getInstance().getInstanceId() + "\"}";

        return Response.ok(instanceId).build();
    }


}