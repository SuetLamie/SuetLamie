package com.surpass.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class XmlHelper {

	private static EntityResolver defaultEntityResolver = new EntityResolver() {

		public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
				IOException {
			return new InputSource(new ByteArrayInputStream(new byte[0]));
		}

	};

	private XmlHelper() {
	}

	/**
	 * returns a document width instream object.
	 */
	public static Document read(InputStream instream) throws DocumentException {
		return read(instream, null);
	}

	/**
	 * returns a document width filename.
	 */
	public static Document read(String filename) throws DocumentException {
		if (filename == null || filename.trim().length() == 0) {
			throw new IllegalArgumentException("filename is null");
		}

		File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			throw new DocumentException(filename + " is not found");
		}

		return getSAXReader(null).read(file);
	}

	/**
	 * returns a document width instream and resolver object.
	 */
	public static Document read(InputStream instream, EntityResolver entityResolver)
			throws DocumentException {
		if (instream == null) {
			throw new IllegalArgumentException("instream is null");
		}
		return getSAXReader(entityResolver).read(instream);
	}

	/**
	 * returns elements in the document with xpath.
	 */
	public static List<Element> elements(Document document, String xpath, String... values) {
		return document.selectNodes(convert(xpath, values));
	}

	/**
	 * returns elements in the element with xpath.
	 */
	public static List<Element> elements(Element element, String xpath, String... values) {
		return element.selectNodes(convert(xpath, values));
	}

	/**
	 * returns single element in the document with xpath.
	 */
	public static Element singleElement(Document document, String xpath, String... values) {
		return (Element) document.selectSingleNode(convert(xpath, values));
	}

	/**
	 * returns single element in the element with xpath.
	 */
	public static Element singleElement(Element element, String xpath, String... values) {
		return (Element) element.selectSingleNode(convert(xpath, values));
	}

	/**
	 * returns single element text value.
	 */
	public static String textTrim(Document document, String xpath, String... values) {
		Element el = singleElement(document, xpath, values);
		return el == null ? null : el.getTextTrim();
	}

	/**
	 * returns single element text value.
	 */
	public static String textTrim(Element element, String xpath, String... values) {
		Element el = singleElement(element, xpath, values);
		return el == null ? null : el.getTextTrim();
	}

	/**
	 * returns single element attribute value.
	 */
	public static String attributeValue(Document document, String name, String xpath,
			String... values) {
		Element el = singleElement(document, xpath, values);
		return el == null ? null : el.attributeValue(name);
	}

	/**
	 * returns single element attribute value.
	 */
	public static String attributeValue(Element element, String name, String xpath,
			String... values) {
		Element el = singleElement(element, xpath, values);
		return el == null ? null : el.attributeValue(name);
	}

	// -----------------------------------------------------------------------------------

	private static SAXReader getSAXReader(EntityResolver entityResolver) {
		SAXReader saxReader = new SAXReader();
		saxReader.setValidation(false);

		if (entityResolver == null) {
			entityResolver = defaultEntityResolver;
		}

		if (entityResolver != null) {
			saxReader.setEntityResolver(entityResolver);
		}
		return saxReader;
	}

	private static String convert(String xpath, String... values) {
		if (values == null || values.length == 0) {
			return xpath;
		}

		if (!isMatching(xpath, values)) {
			throw new IllegalArgumentException("the number of values and '?'s are NOT matching");
		}

		StringBuffer buf = new StringBuffer(xpath);
		StringBuffer results = new StringBuffer("");
		int index = 0;
		while (buf.length() > 0) {
			char c = buf.charAt(0);
			if (c == '?') {
				results.append("'" + values[index] + "'");
				index++;
			} else {
				results.append(c);
			}

			buf.deleteCharAt(0);
		}

		return results.toString();
	}

	private static boolean isMatching(String xpath, String... values) {
		int qs = questionSize(xpath);
		int length = values.length;

		return qs == length;
	}

	private static int questionSize(String xpath) {
		StringBuffer buf = new StringBuffer(xpath);
		int index = 0;
		while (buf.length() > 0) {
			if (buf.charAt(0) == '?') {
				index++;
			}
			buf.deleteCharAt(0);
		}
		return index;
	}

}
