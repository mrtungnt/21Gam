package com.example.twentyonegam

import javax.inject.Inject

enum class StaffRole {
    MANAGER,
    SUPERVISOR,
    EMPLOYEE,
}

enum class StaffDivision {
    BAR,
    TECH,
    SUP,
    RECEPTION,
}

data class WorkListItem @Inject constructor(var division: StaffDivision, var itemName: String)

data class CheckList @Inject constructor(
    val workListItem: WorkListItem,
    var checked: Boolean = false
)


val tech_work_item_literature = listOf("Hút mùi","Điều hòa 25 độ","Đèn các khu","Nhạc đầu giờ âm lượng 80db", "Moving tĩnh đầu giờ")
