package com.zmap.zmap.framework.base.model;

public class SyncDataBaseModel {
	
	private String id;
	private String msg;//消息内容
	private String msg_type;
	private String msg_status;
	private String msg_ds;
	private String msg_des;
	private String msg_error;
	private String msg_result;
	private String read_or_write;
	private String create_time;
	private String create_userid;
	private String update_time;
	
	
	public String getCreate_userid() {
		return create_userid;
	}
	public void setCreate_userid(String create_userid) {
		this.create_userid = create_userid;
	}
	
	public String getRead_or_write() {
		return read_or_write;
	}
	public void setRead_or_write(String read_or_write) {
		this.read_or_write = read_or_write;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_status() {
		return msg_status;
	}
	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}
	
	public String getMsg_ds() {
		return msg_ds;
	}
	public void setMsg_ds(String msg_ds) {
		this.msg_ds = msg_ds;
	}
	public String getMsg_des() {
		return msg_des;
	}
	public void setMsg_des(String msg_des) {
		this.msg_des = msg_des;
	}
	public String getMsg_error() {
		return msg_error;
	}
	public void setMsg_error(String msg_error) {
		this.msg_error = msg_error;
	}
	public String getMsg_result() {
		return msg_result;
	}
	public void setMsg_result(String msg_result) {
		this.msg_result = msg_result;
	}

}
