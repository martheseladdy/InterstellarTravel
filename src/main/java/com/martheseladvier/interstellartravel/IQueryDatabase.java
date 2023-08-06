package com.martheseladvier.interstellartravel;
import java.util.List;

public interface IQueryDatabase{
    Accelerator getAccelerator(String id) throws Exception;
    List<Accelerator> getAllAccelerators() throws Exception;
}
