package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午2:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfessionalTitles {
    private Integer id;
    private String titleName;
    private String description;
}
