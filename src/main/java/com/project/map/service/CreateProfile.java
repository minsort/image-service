package com.project.map.service;

import com.project.map.dto.ProfileDto;
import com.project.map.model.ProfileModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class CreateProfile {

    public BufferedImage createProfile(ProfileDto profileDto) throws Exception {
        String finalSvg = "";

        //Строка содержащая svg файл
        String fileSvgString = convertSvgToString("static/profile.svg");

        ProfileModel profileModel = Convert.convertToProfileModel(profileDto);

            finalSvg = fileSvgString.replace(
                    "idthis", profileModel.getId())
                    .replace("nibthis", profileModel.getId())
                    .replace("luasthis", profileModel.getId())
                    .replace("hakthis", profileModel.getId())
                    .replace("rdtrthis", profileModel.getId())
                    .replace("latlonthis", profileModel.getId())
                    .replace("namethis", profileModel.getId())
                    .replace("contactthis", profileModel.getId())
                    .replace("phonethis", profileModel.getId())
                    .replace("emailthis", profileModel.getId());

        //Сохраняем файл svg
        convertAndSaveStringToSvg(finalSvg);

        //byte[] byteProfile = svgToByteArray("src/main/resources/output/output.svg");

        return byteArrayToBufferedImage(finalSvg.getBytes());
    }

    private void convertAndSaveStringToSvg(String fileString){
        try {
            // Создаем фабрику и построитель документов
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Преобразуем строку SVG в объект Document
            InputSource inputSource = new InputSource(new StringReader(fileString));
            Document document = builder.parse(inputSource);

            // Укажите путь к файлу, в который вы хотите сохранить SVG
            File outputFile = new File("src/main/resources/output/output.svg");

            // Создаем объект Transformer для сохранения документа в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);

            // Сохраняем документ в файл
            transformer.transform(source, result);

            System.out.println("Файл SVG успешно сохранен.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Создания рисунка из массива байт SVG
    public static BufferedImage byteArrayToBufferedImage(byte[] byteArray) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        return ImageIO.read(bis);
    }

    public static byte[] svgToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            // Убедимся, что данные SVG прочитаны с правильной кодировкой (UTF-8)
            String svgContent = bos.toString(StandardCharsets.UTF_8);
            bos.reset();
            bos.write(svgContent.getBytes(StandardCharsets.UTF_8));

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertSvgToString(String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            byte[] svgBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(svgBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
