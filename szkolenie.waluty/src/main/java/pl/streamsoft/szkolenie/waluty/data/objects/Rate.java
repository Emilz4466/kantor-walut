package pl.streamsoft.szkolenie.waluty.data.objects;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


public class Rate {
	@JacksonXmlElementWrapper(localName = "No")
	private String no = null;
	@JacksonXmlElementWrapper(localName = "EffectiveDate")
	private LocalDate effectiveDate;
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
	public void setNo(String no) {
		this.no = no;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = LocalDate.parse(effectiveDate);
	}
	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}
	

	
}
