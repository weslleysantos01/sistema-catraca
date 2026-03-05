package com.catraca.SistemaCatraca;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private UsuarioRepository repository;

    // GET /qrcode/{id} — retorna o QR Code como imagem PNG
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> gerarQRCode(@PathVariable String id) {
        return repository.findById(id)
                .map(u -> {
                    try {
                        QRCodeWriter writer = new QRCodeWriter();
                        BitMatrix bitMatrix = writer.encode(u.getId(), BarcodeFormat.QR_CODE, 300, 300);

                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);

                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.IMAGE_PNG);

                        return ResponseEntity.ok()
                                .headers(headers)
                                .body(out.toByteArray());

                    } catch (Exception e) {
                        return ResponseEntity.internalServerError().<byte[]>build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
