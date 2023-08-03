package com.martheseladvier.interstellartravel;
import java.util.List;

public interface DatabaseInterface {
    public Accelerator getAccelerator(int id);
    public List<Accelerator> getAllAccelerators();
}
