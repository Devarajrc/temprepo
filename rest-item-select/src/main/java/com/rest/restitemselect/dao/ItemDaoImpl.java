package com.rest.restitemselect.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rest.restitemselect.constants.StatusCode;
import com.rest.restitemselect.entities.Item;
import com.rest.restitemselect.mapper.ItemRowMapper;
import com.rest.restitemselect.responses.Response;
import com.rest.restitemselect.utils.CommonUtils;

@Repository
public class ItemDaoImpl implements ItemDao {

	private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Response saveItem(Item item) {
		Response response = CommonUtils.getResponseObject("Add item data");
		try {
			String sql = "INSERT INTO item (id,item_name,price) VALUES(?,?,?)";
			int res = jdbcTemplate.update(sql, new Object[] { item.getId(), item.getItemName(), item.getPrice() });
			if (res == 1) {
				response.setStatus(StatusCode.SUCCESS.name());
			} else {
				response.setStatus(StatusCode.ERROR.name());
			}
		} catch (Exception e) {
			logger.error("Exception in saveEmployee", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}

	@Override
	public List<Item> getItems() {

		String sql = "SELECT id, item_name, price FROM item";
		RowMapper<Item> rowMapper = new ItemRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void serveItem(String item_name) {
		String sql = "DELETE FROM item WHERE item_name=?";
		jdbcTemplate.update(sql, item_name);
	}

}
