package com.martheseladvier.interstellartravel;
import java.util.List;

public interface IQueryDatabase {
    public Accelerator getAccelerator(String id);
    public List<Accelerator> getAllAccelerators();
}
