package interfaces;

public interface Transports {
    public String getTransportName();
    public String getTransportDescription();
    public int getTransportPrice();

    public Long getId();
    public void setId(Long id);
}
