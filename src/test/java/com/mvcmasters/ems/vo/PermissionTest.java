package com.mvcmasters.ems.vo;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class PermissionTest {

    private Permission permission;

    @Before
    public void setUp() {
        permission = new Permission();
    }

    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        permission.setId(id);
        assertEquals(id, permission.getId());
    }

    @Test
    public void testSetAndGetRoleId() {
        Integer roleId = 2;
        permission.setRoleId(roleId);
        assertEquals(roleId, permission.getRoleId());
    }

    @Test
    public void testSetAndGetModuleId() {
        Integer moduleId = 3;
        permission.setModuleId(moduleId);
        assertEquals(moduleId, permission.getModuleId());
    }

    @Test
    public void testSetAndGetAclValue() {
        String aclValue = "Read";
        permission.setAclValue(aclValue);
        assertEquals(aclValue.trim(), permission.getAclValue());

        // Testing with a string that includes whitespace
        String aclValueWithSpace = " Write ";
        permission.setAclValue(aclValueWithSpace);
        assertEquals(aclValueWithSpace.trim(), permission.getAclValue());

        // Testing with null
        permission.setAclValue(null);
        assertNull(permission.getAclValue());
    }

    @Test
    public void testSetAndGetCreateDate() {
        Date createDate = new Date();
        permission.setCreateDate(createDate);
        assertEquals(createDate, permission.getCreateDate());
    }

    @Test
    public void testSetAndGetUpdateDate() {
        Date updateDate = new Date();
        permission.setUpdateDate(updateDate);
        assertEquals(updateDate, permission.getUpdateDate());
    }
}

