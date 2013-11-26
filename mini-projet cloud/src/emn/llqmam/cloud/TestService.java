package emn.llqmam.cloud;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;
import emn.llqmam.cloud.tools.Tools;

public class TestService {

	public static void Main(String[] args) {
		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login("node1_1", "oneadmin",
				"5bd7fcf39891cdff5896e10a79b7cd9e", Tools.get_IP() + ":2633");
		System.out.println("Version d'OpenNebula : " + vm.get_version());
		System.out
				.print("Vérification de la compatibilité du client avec la version d'OpenNebula... ");
		System.out.println(vm.checkCompatibility());
		System.out
				.println("Nombre de machines virtuelles hébergées sur l'infrastructure : "
						+ vm.retrieveVMsInfo().size());
		for (Vm iVM : vm.retrieveVMsInfo()) {
			System.out.println("ID : " + iVM.get_ID());
			System.out.println("Nom : " + iVM.get_name());
			System.out.println("Statut : " + iVM.get_status());
			System.out
					.println("Nom et id du noeud sur lequel la VM est hébergée : "
							+ iVM.open_nebula_vm
									.xpath("HISTORY_RECORDS/HISTORY[last()]/HID")
							+ " "
							+ iVM.open_nebula_vm
									.xpath("HISTORY_RECORDS/HISTORY[last()]/HOSTNAME"));
			System.out.println("Consommation en ressources processeur : "
					+ iVM.open_nebula_vm.xpath("TEMPLATE/CPU"));
			System.out.println("Consommation en ressources mémoire : "
					+ iVM.open_nebula_vm.xpath("TEMPLATE/MEMORY"));
		}

		System.out.println("Nombre de noeuds : "
				+ vm.retrieveNodesInfo().size());
		for (Host iH : vm.retrieveNodesInfo()) {
			System.out.println("ID : " + iH.getId());
			System.out.println("Nom : " + iH.getName());
			System.out.println("Etat : " + iH.stateStr());
			System.out.println("Hyperviseur installé : "
					+ iH.xpath("TEMPLATE/HYPERVISOR"));
			System.out.println("Capacité en ressources processeur : "
					+ iH.xpath("TEMPLATE/TOTALCPU"));
			System.out.println("Capacité en ressources mémoire : "
					+ iH.xpath("TEMPLATE/TOTALMEMORY"));
			System.out.println("Quantité de processeur utilisée : "
					+ iH.xpath("TEMPLATE/USEDCPU"));
			System.out.println("Quantité de mémoire utilisée : "
					+ iH.xpath("TEMPLATE/USEDMEMORY"));
			System.out.println("Quantité de processeur libre : "
					+ iH.xpath("TEMPLATE/FREECPU"));
			System.out.println("Quantité de mémoire libre : "
					+ iH.xpath("TEMPLATE/FREEMEMORY"));
		}
	}

}
