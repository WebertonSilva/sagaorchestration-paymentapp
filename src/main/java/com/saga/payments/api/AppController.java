package com.saga.payments.api;


import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.services.PaymentServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("payments")
public class AppController {

    @Autowired
    PaymentServices paymentServices;

    @Operation(summary = "Make a payment for an order")
    @PostMapping("/pay")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment done with successful!! Payment ID : <>",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload",
                    content = @Content) })
    public ResponseEntity pay(@RequestBody Payment payment){

        if (payment.equals(null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            paymentServices.pay(payment);
            return ResponseEntity.ok(payment.getPaymentId());
        }
    }

    @Operation(summary = "Cancel a payment by id")
    @PostMapping("/cancelPayment/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment canceled!!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameter",
                    content = @Content) })
    public ResponseEntity cancelPayment(@PathVariable String id){

        if (id.equals(null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            paymentServices.cancelPaymentById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Payment canceled!!");
        }
    }


    @Operation(summary = "Get a payment by id")
    @GetMapping("/getPayment/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Found!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameter",
                    content = @Content) })
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable String id){

        if (id.equals(null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            PaymentDTO paymentDTO = paymentServices.getPaymentById(id);
            if (paymentDTO != null){
                return ResponseEntity.status(HttpStatus.FOUND).body(paymentDTO);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }
    }

}
