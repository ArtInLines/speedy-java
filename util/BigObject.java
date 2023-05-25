package util;

import java.util.Objects;

public class BigObject {
	private Integer i = 69;
	private Double d = 420.69D;
	private String s = "Hello World abcwoswlufsldniewm fjklsf iew";
	private char c = 'x';
	private Long l = 69696969L;
	private Float f = 69.420F;

	public BigObject(int i) {
		this.c = s.charAt(i % s.length());
		this.i = i;
	}

	public BigObject(Integer i, Double d, String s, char c, Long l, Float f) {
		this.i = i;
		this.d = d;
		this.s = s;
		this.c = c;
		this.l = l;
		this.f = f;
	}

	public Integer getI() {
		return this.i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Double getD() {
		return this.d;
	}

	public void setD(Double d) {
		this.d = d;
	}

	public String getS() {
		return this.s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public char getC() {
		return this.c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public Long getL() {
		return this.l;
	}

	public void setL(Long l) {
		this.l = l;
	}

	public Float getF() {
		return this.f;
	}

	public void setF(Float f) {
		this.f = f;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof BigObject)) {
			return false;
		}
		BigObject bigObject = (BigObject) o;
		return Objects.equals(i, bigObject.i) && Objects.equals(d, bigObject.d) && Objects.equals(s, bigObject.s)
				&& c == bigObject.c && Objects.equals(l, bigObject.l) && Objects.equals(f, bigObject.f);
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, d, s, c, l, f);
	}

	@Override
	public String toString() {
		return "{" +
				" i='" + getI() + "'" +
				", d='" + getD() + "'" +
				", s='" + getS() + "'" +
				", c='" + getC() + "'" +
				", l='" + getL() + "'" +
				", f='" + getF() + "'" +
				"}";
	}
}
