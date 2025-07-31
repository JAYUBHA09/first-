//data class InOutResponse(
//    val status: String,
//    val message: String,
//    val data: InOutDataWrapper
//)
//
//data class InOutDataWrapper(
//    val in_out_data: List<InOutEntry>,
//    val reports: Reports
//)
//
//data class InOutEntry(
//    val id: Int?,
//    val employee_id: Int?,
//    val punch_in_time: String?,
//    val punch_out_time: String?,
//    val punch_date: String?,
//    val punch_type: Int?,
//    val punch_location: String?,
//    val qrcode_hash: String?,
//    val is_manual: Int?,
//    val is_left: Int?,
//    val created_at: String?,
//    val updated_at: String?,
//    val total_hours: String? // only present in in_out_data, not in alldata
//)
//
//data class Reports(
//    val punch_date: String?,
//    val employee_id: String?,
//    val pressent: Int?,
//    val absent: Int?,
//    val halfday: Int?,
//    val total: Int?,
//    val working_hours: String?,
//    val alldata: List<InOutEntry>?, // ✅ corrected
//    val total_working_time: String?,
//    val total_week_working_time: String?
//)
