/**
 *苦心人，天不负
 */
package ltd.newbee.mall.entity;

import lombok.Data;

/**
 * 库存修改所需实体
 */

@Data
public class StockNumDTO {
    private Long goodsId;

    private Integer goodsCount;
}
