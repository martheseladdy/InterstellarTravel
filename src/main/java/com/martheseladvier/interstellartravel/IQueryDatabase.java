package com.martheseladvier.interstellartravel;
import java.util.List;

public interface IQueryDatabase{
    public Accelerator getAccelerator(String id) throws Exception;
    public List<Accelerator> getAllAccelerators() throws Exception;
}
