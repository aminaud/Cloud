package emn.llqmam.cloud.services;

import emn.llqmam.cloud.data.Vm;

public interface IOpenNebula {

 public Vm login(String user, String password, String ip_address);
}