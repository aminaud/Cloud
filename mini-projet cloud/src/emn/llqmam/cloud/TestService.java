package emn.llqmam.cloud;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;
import emn.llqmam.cloud.tools.Tools;

public class TestService {

	public static void Main (String[] args) {
		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login("node1_1", "oneadmin",
				"5bd7fcf39891cdff5896e10a79b7cd9e", Tools.get_IP() + ":2633");
		System.out.println("Version d'OpenNebula : " + vm.get_version());
		System.out.print("V�rification de la compatibilit� du client OCA avec la version d'OpenNebula... ");
		System.out.println(vm.checkCompatibility());
		System.out
				.println("Nombre de machines virtuelles h�berg�es sur l'infrastructure : "
						+ vm.retrieveVMsInfo().size());
		for (Vm iVM : vm.retrieveVMsInfo()) {
			System.out.println("ID : " + iVM.get_ID());
			System.out.println("Nom : " + iVM.get_name());
			System.out.println("Statut : " + iVM.get_status());
			System.out
					.println("Nom et id du noeud sur lequel la VM est h�berg�e : "
							+ iVM.open_nebula_vm.uid() + " " + iVM.open_nebula_vm.xpath("UNAME"));
			System.out.println("Consommation en ressources processeur : "
					+ iVM.open_nebula_vm.xpath("TEMPLATE/CPU"));
			System.out.println("Consommation en ressources m�moire : "
					+ iVM.open_nebula_vm.xpath("TEMPLATE/MEMORY"));
		}

		System.out.println("Nombre de noeuds : "
				+ vm.retrieveNodesInfo().size());
		for (Host iH : vm.retrieveNodesInfo()) {
			System.out.println("ID : " + iH.getId());
			System.out.println("Nom : " + iH.getName());
			System.out.println("Etat : " + iH.stateStr());
			System.out.println("Hyperviseur install� : "
					+ iH.xpath("TEMPLATE/HYPERVISOR"));
			System.out.println("Capacit� en ressources processeur : "
					+ iH.xpath("TEMPLATE/TOTALCPU"));
			System.out.println("Capacit� en ressources m�moire : "
					+ iH.xpath("TEMPLATE/TOTALMEMORY"));
			System.out.println("Quantit� de processeur utilis�e : "
					+ iH.xpath("TEMPLATE/USEDCPU"));
			System.out.println("Quantit� de m�moire utilis�e : "
					+ iH.xpath("TEMPLATE/USEDMEMORY"));
			System.out.println("Quantit� de processeur libre : "
					+ iH.xpath("TEMPLATE/FREECPU"));
			System.out.println("Quantit� de m�moire libre : "
					+ iH.xpath("TEMPLATE/FREEMEMORY"));
		}
	}
	
}
