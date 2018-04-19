package com.izhuantou.damain.mongo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.izhuantou.damain.BasePojo;

@Document(collection = "testUser")
@CompoundIndexes({ @CompoundIndex(name = "index_unique", def = "{'name': 1, 'birthday': 1}", unique = true) })
public class TestUser extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Indexed
    private String name;
    private Date birthday;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getBirthday() {
	return birthday;
    }

    public void setBirthday(Date birthday) {
	this.birthday = birthday;
    }

    @Override
    public String toString() {
	return "User[id=" + id + ",name=" + name + ",birthday=" + birthday + "]";
    }

}
