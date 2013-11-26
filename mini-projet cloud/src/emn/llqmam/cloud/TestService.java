package emn.llqmam.cloud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opennebula.client.Client;
import org.opennebula.client.host.Host;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;
import emn.llqmam.cloud.tools.Tools;

public class TestService {

	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test2() {
		Client oneClient;
		String user = "testuser", password = "testpass";
//		String user = "oneadmin", password = "5bd7fcf39891cdff5896e10a79b7cd9e";
		String ip_address = Tools.get_IP();
		 
		try
		{
		    oneClient = new Client(user+":"+password, "http://" + ip_address + ":2633/RPC2");
		 
		    System.out.print("Trying to allocate the virtual machine... ");
		    VirtualMachinePool vmp = new VirtualMachinePool(oneClient, VirtualMachinePool.ALL_VM);
		 
		    vmp.info();
		    
		    List<VirtualMachine> listVM = new ArrayList<>();
		    Iterator<VirtualMachine> i = vmp.iterator();
		    
		    while (i.hasNext()) {
				VirtualMachine vm = i.next();
				listVM.add(vm);
			}
		    
		    System.out.println("Nb VM : " + listVM.size());
		 
		}
		catch (Exception e)
		{
		    System.out.println(e.getMessage());
		}
	}
	
	private static void test1 () {
		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login("oneadmin", "5bd7fcf39891cdff5896e10a79b7cd9e", Tools.get_IP() + ":2633");
//		vm = on.login("testuser", "testpass", Tools.get_IP() + ":2633");
		System.out.println("Version d'OpenNebula : " + vm.get_version());
		System.out.print("Verification de la compatibilite du client avec la version d'OpenNebula... ");
		System.out.println(vm.checkCompatibility());
		System.out.println("Nombre de machines virtuelles hebergees sur l'infrastructure : " + vm.retrieveVMsInfo().size());
		for (Vm iVM : vm.retrieveVMsInfo()) {
			System.out.println(iVM);
			System.out.println("ID : " + iVM.get_open_nebula_vm().getId());
			System.out.println("Nom : " + iVM.get_name());
			System.out.println("Statut : " + iVM.get_status());
			System.out.println("Nom et id du noeud sur lequel la VM est hebergee : "
							+ iVM.open_nebula_vm.xpath("HISTORY_RECORDS/HISTORY[last()]/HID")
							+ " "
							+ iVM.open_nebula_vm.xpath("HISTORY_RECORDS/HISTORY[last()]/HOSTNAME"));
			System.out.println("Consommation en ressources processeur : " + iVM.open_nebula_vm.xpath("TEMPLATE/CPU"));
			System.out.println("Consommation en ressources memoire : " + iVM.open_nebula_vm.xpath("TEMPLATE/MEMORY"));
		}

		System.out.println("Nombre de noeuds : " + vm.retrieveNodesInfo().size());
		for (Host iH : vm.retrieveNodesInfo()) {
			System.out.println("ID : " + iH.getId());
			System.out.println("Nom : " + iH.getName());
			System.out.println("Etat : " + iH.stateStr());
			System.out.println("Hyperviseur installr : " + iH.xpath("TEMPLATE/HYPERVISOR"));
			System.out.println("Capacite en ressources processeur : " + iH.xpath("TEMPLATE/TOTALCPU"));
			System.out.println("Capacite en ressources memoire : " + iH.xpath("TEMPLATE/TOTALMEMORY"));
			System.out.println("Quantite de processeur utilisee : " + iH.xpath("TEMPLATE/USEDCPU"));
			System.out.println("Quantite de memoire utilisee : " + iH.xpath("TEMPLATE/USEDMEMORY"));
			System.out.println("Quantite de processeur libre : " + iH.xpath("TEMPLATE/FREECPU"));
			System.out.println("Quantite de memoire libre : " + iH.xpath("TEMPLATE/FREEMEMORY"));
		}
	}
}
