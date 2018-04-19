package com.izhuantou.damain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 修改密码实体类
 *
 * @author fucheng
 * @date 2018-01-25
 */
@Table(name = "tb_definition")
public class Definition extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;

    /**
     * 编码定义名称
     */
    @Column
    private String name;
    /**
     * 编码定义中文名称
     */
    @Column(name = "name_cn")
    private String nameCn;

    public String getOid() {
	return oid;
    }

    public void setOid(String oid) {
	this.oid = oid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNameCn() {
	return nameCn;
    }

    public void setNameCn(String nameCn) {
	this.nameCn = nameCn;
    }

    @Override
    public String toString() {
	return "Definition [oid=" + oid + ", name=" + name + ", nameCn=" + nameCn + "]";
    }

}
