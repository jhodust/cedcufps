package com.ufps.cedcufps.utils;

import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public class ManejoPdf {
	private static final Logger logger = LoggerFactory.getLogger(ManejoPdf.class);

	private static final String header="header_pdf.png";
	private static final String footer="footer_pdf.png";
	private static final int CANT_PERSONAS_POR_HOJA=19;
	public static ByteArrayInputStream generarPDFParticipantes(List<ParticipanteDto> participantes, EducacionContinua e, Path dirFolderImgs) {

		Document document = new Document(PageSize.LETTER, 50, 50, 100, 100);//creo el documento con margenes para la información (titulo, subtitulo, tabla)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
	
		try {
			PdfWriter writer=PdfWriter.getInstance(document, out);

			document.open();

			int totalParticipantes = participantes.size();
			int hojas = totalParticipantes / CANT_PERSONAS_POR_HOJA;
			if (totalParticipantes % CANT_PERSONAS_POR_HOJA > 0) {
				hojas++;
			}
			int k = 0;//variable para recorrer las paginas
			int i = 1;
			int j = CANT_PERSONAS_POR_HOJA;//variable para recorrer la cantidad de participantes por grupos de 20
			while (k < hojas) {
				
				/***********************titulo**************************/
				String tituloDoc=e.getTipoEduContinua().getTipoEduContinua()+": "+e.getNombre();
				
				Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
				Chunk chunk = new Chunk(tituloDoc.toUpperCase(), font);

				Phrase phrase = new Phrase();
				phrase.add(chunk);

				Paragraph titulo = new Paragraph();
				titulo.add(phrase);
				titulo.setAlignment(Element.ALIGN_CENTER);
				
				document.add(titulo);
				/***********************sub titulo**************************/
				document.add(Chunk.NEWLINE);
				font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Font.UNDERLINE);
				chunk = new Chunk("Listado Inscritos", font);

				phrase = new Phrase();
				phrase.add(chunk);

				phrase = new Phrase();
				phrase.add(chunk);

				Paragraph subtitulo = new Paragraph();
				subtitulo.add(phrase);
				subtitulo.setAlignment(Element.ALIGN_CENTER);
				document.add(subtitulo);
				document.add(Chunk.NEWLINE);

				/***********************tabla participantes **************************/
				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100);
				table.setWidths(new int[] { 1, 4, 6, 6 });
				
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

				PdfPCell hcell;
				hcell = new PdfPCell(new Phrase("N°", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Documento", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Nombre", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Email", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				//hcell = new PdfPCell(new Phrase("Participante", headFont));
				//hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//table.addCell(hcell);
				while (i <= j) {
					if (i <= participantes.size()) {
						
						ParticipanteDto p = participantes.get(i - 1);
						
						PdfPCell cell;

						cell = new PdfPCell(new Phrase(String.valueOf(i)));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(p.getTipoDocumento() + " - " + p.getNumeroDocumento()));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(p.getNombrePersona()));
						cell.setPaddingLeft(5);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(p.getEmail()));
						cell.setPaddingLeft(5);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell);

						//cell = new PdfPCell(new Phrase(p.getPersona().getTipoPersona().getTipoPersona()));
						//cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						//cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						//cell.setPaddingRight(5);
						//table.addCell(cell);

						i++;
					}else {
						break;
					}
				}
				document.add(table);
				/************************encabezado fijado***********************************/
				/*cargo la imagen*/
				
				Path path = dirFolderImgs.resolve(header);
				Image img = Image.getInstance(path.toAbsolutePath().toString());
				
				/*preparo la tabla*/
				PdfPTable header = new PdfPTable(1);//preparo la tabla
				header.getDefaultCell().setBorder(Rectangle.NO_BORDER);//elimino el borde de la celda
				header.addCell(img);//agrego la imagen a la celda
				header.setTotalWidth(document.getPageSize().getWidth());//actualizo el ancho de la tabla al tamaño de la hoja
				header.writeSelectedRows(0, -1, 0,
						document.getPageSize().getHeight(),
					    writer.getDirectContent());//fijo el encabezado a la parte superior de la hoja
				
				/**************************footer fijado**************************************/
				/*cargo la imagen*/
				path = dirFolderImgs.resolve(footer);
				
				img = Image.getInstance(path.toAbsolutePath().toString());
				
				PdfPTable pie = new PdfPTable(1);//preparo la tabla
				pie.getDefaultCell().setBorder(Rectangle.NO_BORDER);//elimino el borde de la celda
				pie.addCell(img);//agrego la imagen a la celda
				pie.setTotalWidth(document.getPageSize().getWidth());//actualizo el ancho de la tabla al tamaño de la hoja
				pie.writeSelectedRows(0, -1, 0,
					    pie.getTotalHeight(),
					    writer.getDirectContent());//fijo el footer a la parte superior de la hoja
				
				
				k++;
				j = j + CANT_PERSONAS_POR_HOJA;
				document.add(Chunk.NEXTPAGE);
			}
			
			document.close();

		} catch (Exception ex) {

			logger.error("Error occurred: {0}", ex);
		}
		return new ByteArrayInputStream(out.toByteArray());

	}

	public static void generarPDFParticipantes2(List<Participante> participantes, EducacionContinua e) {

		XWPFDocument document = new XWPFDocument();

		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Build Your REST API with Spring");
		titleRun.setColor("009933");
		titleRun.setBold(true);
		titleRun.setFontFamily("Courier");
		titleRun.setFontSize(20);

		XWPFParagraph subTitle = document.createParagraph();
		subTitle.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun subTitleRun = subTitle.createRun();
		subTitleRun.setText("from HTTP fundamentals to API Mastery");
		subTitleRun.setColor("00CC44");
		subTitleRun.setFontFamily("Courier");
		subTitleRun.setFontSize(16);
		subTitleRun.setTextPosition(20);
		subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
		String output = "rest-with-spring.docx";
		try {
			FileOutputStream out;

			out = new FileOutputStream(output);

			document.write(out);
			out.close();
			document.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	public static ByteArrayInputStream generarPDFDiplomas(String imagenDiploma) {

		Document document = new Document(PageSize.LETTER.rotate(), 40, 30, 30, 50);//creo el documento con margenes para la información (titulo, subtitulo, tabla)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
	
		try {
			PdfWriter writer=PdfWriter.getInstance(document, out);

			document.open();

			Path path = Paths.get(imagenDiploma);
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			//if you would have a chapter indentation
			/*int indentation = 0;
			//whatever

			float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
			               - document.rightMargin() - indentation) / img.getWidth()) * 100;

			img.scalePercent(scaler);
			*/
			img.scaleToFit(document.getPageSize().getWidth()-document.rightMargin(),document.getPageSize().getHeight()-document.bottomMargin()); 
			document.add(img);
			
			document.close();
		} catch (Exception ex) {

			logger.error("Error occurred: {0}", ex);
		}
		return new ByteArrayInputStream(out.toByteArray());

	}
}
