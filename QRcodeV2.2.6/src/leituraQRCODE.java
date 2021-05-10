
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jardel
 */
public class leituraQRCODE {
    
    String nomeArquivoQrCode = "jardel.png";
    //try
    //BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(nomeArquivoQrCode)))));
    //Result decodificado = new MultiFormatReader().decode(bitmap);
    //System.out.println("Qr code: "+ decodificado.getText());
    //catch
    //ex.printStackTracer();
    
    
}
