package com.example.DisneyBookingBackend.models;

public enum Status {
    /**
     * 已确认 —— 用户预订成功，酒店已确认保留房间
     * 说明：支付成功或人工审核通过，订单/预订正式生效
     * 用户不可随意取消（或需按规则扣费）
     */
    CONFIRMED("已确认"),

    /**
     * 待处理 —— 用户提交了预订，但尚未支付或等待人工审核
     * 说明：订单处于中间状态，可能因超时未支付自动取消
     * 用户通常可在此阶段免费取消
     */
    PENDING("待处理"),

    /**
     * 已取消 —— 用户主动取消，或系统自动取消（如超时未支付）
     * 说明：订单无效，房间资源释放
     * 可能有“取消时间”、“取消原因”等附加信息
     */
    CANCELLED("已取消"),

    /**
     * 已完成 —— 用户已入住并离店，订单生命周期结束
     * 说明：通常在 check-out 后自动触发
     * 用于统计、评价、开票等后续流程
     */
    COMPLETED("已完成");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
