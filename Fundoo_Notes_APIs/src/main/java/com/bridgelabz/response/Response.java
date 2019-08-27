package com.bridgelabz.response;



public class Response 

{	
    private int statusCode;
    private String statusMessage;
	private Object data;
	
	
    public Response() {}
    public Response(int statusCode, String string, Object data)
        {
			this.statusCode = statusCode;
			this.statusMessage = string;
			this.data = data;
		}

	public int getStatusCode() 
	{
		return statusCode;
	}

	public void setStatusCode(int statusCode) 
	{
		this.statusCode = statusCode;
	}

	public String getStatusMessage()
	{
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) 
	{
		this.statusMessage = statusMessage;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
    
    
    
    
 
    
    
    
}
