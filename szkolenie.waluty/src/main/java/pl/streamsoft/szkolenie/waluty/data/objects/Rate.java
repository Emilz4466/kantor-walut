package pl.streamsoft.szkolenie.waluty.data.objects;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


public class Rate {
	@JacksonXmlElementWrapper(localName = "No")
	private String no = null;
	@JacksonXmlElementWrapper(localName = "EffectiveDate")
	private LocalDate effectiveDate = null;
	@JacksonXmlElementWrapper(localName = "Mid")
	private BigDecimal mid = null;
	
	public String getNo() {
		return no;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public BigDecimal getMid() {
		return mid;
	}
	
}
