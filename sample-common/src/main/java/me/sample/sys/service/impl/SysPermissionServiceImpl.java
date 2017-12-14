package me.sample.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import me.sample.base.domain.TreeObject;
import me.sample.base.service.SequenceService;
import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysPermission;
import me.sample.sys.mapper.SysPermissionMapper;
import me.sample.sys.service.SysPermissionService;
import me.sample.sys.service.SysRolePermissionService;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
        private static final Logger logger = LoggerFactory.getLogger(SysPermissionService.class);
        
        @Autowired
        private SysPermissionMapper sysPermissionMapper;
        @Autowired
        private SequenceService sequenceService;
        @Autowired
        private SysRolePermissionService sysRolePermissionService;

//        @Override
//        public int deleteByPrimaryKey(Integer id) {
//            int aff = 0;
//            try {
//                aff = sysPermissionMapper.deleteByPrimaryKey(id);
//            } catch (Exception e) {
//                logger.error("", e);
//                throw new ServiceException("delete one sys permission error",e);
//            }
//            return aff;
//        }

        @Override
        public int insert(SysPermission record) {
            int aff = 0;
            try {
                Date now = DateTime.now().toDate();

                Integer id = sequenceService.next().intValue();
                record.setId(id);
                record.setDisabled(Boolean.FALSE);
                record.setDeleted(Boolean.FALSE);
                record.setCrtime(now);

                aff = sysPermissionMapper.insert(record);
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("insert one sys permission error",e);
            }
            return aff;
        }

        @Override
        public SysPermission selectByPrimaryKey(Integer id) {
            SysPermission record = null;
            try {
                record = sysPermissionMapper.selectByPrimaryKey(id);
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("select one sys permission error",e);
            }
            return record;
        }

        @Override
        public List<SysPermission> selectAll() {
            List<SysPermission> records = null;
            try {
                records = sysPermissionMapper.selectAll();
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("select all sys permission error",e);
            }
            return records;
        }

        @Override
        public int updateByPrimaryKey(SysPermission record) {
            int aff = 0;
            try {
                Date now = DateTime.now().toDate();
                record.setMdtime(now);
                aff = sysPermissionMapper.updateByPrimaryKey(record);
            } catch (Exception e) {
                logger.error("{}", e);
                throw new ServiceException("update one sys permission error",e);
            }
            return aff;
        }
        
        @Override
        public int removeByPrimaryKey(SysPermission record) {
            int aff = 0;
            try {
                SysPermission clone = selectByPrimaryKey(record.getId());
                clone.setDeleted(Boolean.TRUE);
                clone.setMduser(record.getMduser());
                aff = updateByPrimaryKey(clone);

                String permissionCode = clone.getPermissionCode();    
//                List<SysRole> sysRoles = sysRolePermissionService.queryByPermissionCode(permissionCode);
//                if(sysRoles.isEmpty()){
                    aff += sysRolePermissionService.deleteByPermissionCode(permissionCode);
//                }else{
//                    throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
//                }
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("remove one sys permission error",e);
            }
            return aff;
            
        }

        private void load(TreeObject item, final List<TreeObject> children) {
            List<SysPermission> list = query(Integer.parseInt(item.getId()));
            if(!list.isEmpty()){
                for (SysPermission sysPermission : list) {
                    children.add(new TreeObject(String.valueOf(sysPermission.getId()), sysPermission.getViewname(),sysPermission.getDisabled()));
                }
                item.setChildren(children);
                
                for (TreeObject itemNode : children) {
                    load(itemNode,itemNode.getChildren());
                }
            }
        }
        
        @Override
        public List<TreeObject> load(Integer pid) {

            List<TreeObject> tree = Lists.newArrayList();
            List<SysPermission> list = query(pid);
            for (SysPermission sysPermission : list) {
                tree.add(new TreeObject(String.valueOf(sysPermission.getId()), sysPermission.getViewname(),sysPermission.getDisabled()));
            }
            
            for (TreeObject item : tree) {
                load(item,item.getChildren());
            }
            
            return tree;
        }
        
        @Override
        public TreeObject getRoot() {
            TreeObject node = null;
            try {
                SysPermission record = sysPermissionMapper.getRoot();
                node = new TreeObject(String.valueOf(record.getId()),record.getViewname(),record.getDisabled());
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("query one sys permission root node error",e);
            }
            return node;
        }

        @Override
        public PageInfo<SysPermission> query(SysPermission record,int pageNum, int pageSize) {
            PageInfo<SysPermission> pi = null;
            try {
                PageHelper.startPage(pageNum, pageSize);
                List<SysPermission> list = sysPermissionMapper.query(record.getId());
                pi = new PageInfo<SysPermission>(list);
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("query sys permission error",e);
            }
            return pi;
        }

        @Override
        public List<SysPermission> query(Integer pid) {
            List<SysPermission> list = null;
            try {
                list = sysPermissionMapper.query(pid);
            } catch (Exception e) {
                logger.error("", e);
                throw new ServiceException("query sys permission by parent id error",e);
            }
            return list;
        }

    
}
