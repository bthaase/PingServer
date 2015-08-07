import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.icmp4j.IcmpPingUtil;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;

@Path("/ping")
public class PingService {
	@GET
	@Produces("application/json")
	public Response testResponse() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("alive", "true");
	 
		return Response.status(200).entity(jsonObject.toString()).build();	
	}
	
	@Path("{a}")
	@GET
	@Produces("application/json")
	public Response pingDevice(@PathParam("a") String address) throws JSONException {		
		JSONObject jsonObject = new JSONObject();

		try { 
			final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();
			request.setHost(address);

			final IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);
			final String formattedResponse = IcmpPingUtil.formatResponse(response);

			jsonObject.put("successFlag", response.getSuccessFlag());
			jsonObject.put("address", response.getHost());
			jsonObject.put("message", response.getErrorMessage());
			jsonObject.put("size", response.getSize());
			jsonObject.put("rtt", response.getRtt());
			jsonObject.put("ttl", response.getTtl());
		} catch(Exception e) {
			jsonObject.put("error", "true");		
		}
			 
		return Response.status(200).entity(jsonObject.toString()).build();
	}
}
