package me.sangdong.booking;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.sangdong.booking.rate.RateGrpc;
import me.sangdong.booking.rate.RatePlan;
import me.sangdong.booking.rate.Request;
import me.sangdong.booking.rate.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HotelController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/greeting")
    public List<RatePlan> greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {

        ServiceInstance serviceInstance = discoveryClient.getInstances("go.micro.srv.rate").get(0);

        final ManagedChannel channel = ManagedChannelBuilder.forAddress(serviceInstance.getHost(), serviceInstance.getPort())
                .usePlaintext()
                .build();

        Request request = Request.newBuilder()
                .addAllHotelIds(Arrays.asList("Hotel 1", "Hotel 2"))
                .setInDate("20200227")
                .setOutDate("20200228")
                .build();

        RateGrpc.RateBlockingStub rateStub = RateGrpc.newBlockingStub(channel);

        Result rsp = rateStub.getRates(request);

        return rsp.getRatePlansList();
    }
}
