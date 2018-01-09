package org.ovirt.engine.core.dao;

import java.util.List;
import java.util.Map;

import org.ovirt.engine.core.common.businessentities.VmStatic;
import org.ovirt.engine.core.compat.Guid;

public interface VmStaticDao extends GenericDao<VmStatic, Guid> {
    /**
     * Gets all static VMs by name.
     *
     * @param name
     *            the vm name
     * @return the list of vms
     */
    List<VmStatic> getAllByName(String name);

    /**
     * Gets all static VMs by Storage Pool Id.
     *
     * @param spId
     *            storage pool id
     * @return the list of vms
     */
    List<VmStatic> getAllByStoragePoolId(Guid spId);

    /**
     * Retrieves all static VMs for the specified VDS group.
     *
     * @param cluster
     *            the VDS group
     * @return the list of VMs
     */
    List<VmStatic> getAllByCluster(Guid cluster);

    /**
     * Not really sure what this method's doing.
     *
     * @param vds
     *            the VDS id
     * @return the list of VMs
     */
    List<VmStatic> getAllWithFailbackByVds(Guid vds);

    /**
     * Retrieves all static VMs for the specified group and name.
     *
     * @param group
     *            the group
     * @param name
     *            the name
     * @return the list of vms
     */
    List<VmStatic> getAllByGroupAndNetworkName(Guid group, String name);

    /**
     * Get the names of VMs pinned to the specified host.
     *
     * @param host
     *            The host's id.
     * @return The names of the VMs which are pinned to the host, or empty if none.
     */
    List<String> getAllNamesPinnedToHost(Guid host);

    /**
     * get the db generation for vm/template with the given guid
     *
     * @param id - vm/template id
     */
    public Long getDbGeneration(Guid id);

    /**
     * Increment the db version for all vms/templates in a specific storage pool.
     */
    public void incrementDbGenerationForAllInStoragePool(Guid storagePoolId);

    /**
     * increment by 1 the generation of the vm/template with the given guid.
     *
     * @param id - vm/template id
     */
    public void incrementDbGeneration(Guid id);

    /**
     * increment by 1 the generation of the vms/templates with the given guids.
     *
     * @param guids - vm/template ids
     */
    public void incrementDbGenerationForVms(List<Guid> guids);

    List<Guid> getOrderedVmGuidsForRunMultipleActions(List<Guid> guids);

    /**
     * remove with optionally remove/keep vm permissions
     * @param id vm to remove
     * @param removePermissions flag to indicate if to remove the permissions or keep them
     */
    public void remove(Guid id, boolean removePermissions);

    /**
     * Retrieves all the ids of the vms and templates that have no attached disks matching the provided criteria.
     *
     * @param storagePoolId
     *            the storage pool id of the vms/templates
     * @param shareableDisks
     *            check for attached shareable disks
     */
    public List<Guid> getVmAndTemplatesIdsWithoutAttachedImageDisks(Guid storagePoolId, boolean shareableDisks);

    /**
     * update vm_static.cpu_profile_id for cluster
     */
    void updateVmCpuProfileIdForClusterId(Guid clusterId, Guid cpuProfileId);

    List<VmStatic> getAllWithoutIcon();


    /**
     * Retrieves all running VMs with a lease on the given storage domain.
     *
     * @param storageDomain
     *            the storage domain's ID
     * @return the running VMs with a lease on the storage domain
     */
    List<VmStatic> getAllRunningWithLeaseOnStorageDomain(Guid storageDomain);

    /**
     * Retrieves all running VMs with an ISO attached as a CDROM on the given storage domain.
     *
     * @param storageDomain
     *            the storage domain's ID
     * @return the running VMs with an ISO on the storage domain
     */
    List<String> getAllRunningNamesWithIsoOnStorageDomain(Guid storageDomain);

    /**
     * Retrieves a list of VMs with a lease on the given storage domain.
     *
     * @param storageDomain
     *            the storage domain id
     * @return the list of VMs with a lease on the storage domain
     */
    List<VmStatic> getAllWithLeaseOnStorageDomain(Guid storageDomain);

    /**
     * Retrieves all running static VMs for the given VDS instance.
     *
     * @param vds
     *            the VDS id
     * @return the list of static vms
     */
    List<VmStatic> getAllRunningForVds(Guid vds);

    void updateVmLeaseInfo(Guid vmId, Map<String, String> leaseInfo);
}
