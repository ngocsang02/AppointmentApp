package com.tutorials.appointmentapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tutorials.appointmentapp.domain.CategoryModel
import com.tutorials.appointmentapp.domain.DoctorsModel


/*
* Lớp này kế thừa từ ViewModel của Android, được sử dụng để lưu trữ
* và quản lý dữ liệu liên quan đến UI, giúp giữ nguyên trạng dữ liệu
* khi thiết bị quay màn hình hoặc khi các Activity/Fragment bị tái tạo.
* */
class MainViewModel():ViewModel() {

    //Đối tượng FirebaseDatabase này sử dụng phương thức getInstance() để lấy thể hiện của
    // Firebase Realtime Database. Đây là nơi mà bạn sẽ lấy dữ liệu.
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _doctors = MutableLiveData<MutableList<DoctorsModel>>()

    val category:LiveData<MutableList<CategoryModel>> = _category
    val doctors:LiveData<MutableList<DoctorsModel>> = _doctors


    //được gọi để lấy dữ liệu từ Firebase Database.
    fun loadCategory(){
        //tham chieu den nhánh "Category" trong Firebase Database.
        val Ref = firebaseDatabase.getReference("Category")

        //ValueEventListener để lắng nghe thay đổi dữ liệu trong "Category".
        Ref.addValueEventListener(object :ValueEventListener{
            //Phương thức này được gọi mỗi khi dữ liệu từ nhánh "Category" thay đổi.
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for(childSnapshot in snapshot.children){
                    //chuyển đổi thành đối tượng CategoryModel bằng cách sử dụng getValue().
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    //Nếu thành công, các đối tượng CategoryModel sẽ được thêm vào danh sách lists
                    // và cập nhật giá trị của _category để UI có thể hiển thị dữ liệu mới.
                    if (list!=null){
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            //Phương thức này được gọi khi việc lấy dữ liệu gặp lỗi.
            // Trong đoạn code của bạn, chức năng xử lý lỗi chưa được triển khai.
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    //được gọi để lấy dữ liệu từ Firebase Database.
    fun loadDoctors(){
        //tham chieu den nhánh "Doctors" trong Firebase Database.
        val Ref = firebaseDatabase.getReference("Doctors")

        //ValueEventListener để lắng nghe thay đổi dữ liệu trong "Doctors".
        Ref.addValueEventListener(object :ValueEventListener{
            //Phương thức này được gọi mỗi khi dữ liệu từ nhánh "Doctors" thay đổi.
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<DoctorsModel>()
                for(childSnapshot in snapshot.children){
                    //chuyển đổi thành đối tượng DoctorsModel bằng cách sử dụng getValue().
                    val list = childSnapshot.getValue(DoctorsModel::class.java)
                    //Nếu thành công, các đối tượng DoctorsModel sẽ được thêm vào danh sách lists
                    // và cập nhật giá trị của _doctors để UI có thể hiển thị dữ liệu mới.
                    if (list!=null){
                        lists.add(list)
                    }
                }
                _doctors.value = lists
            }

            //Phương thức này được gọi khi việc lấy dữ liệu gặp lỗi.
            // Trong đoạn code của bạn, chức năng xử lý lỗi chưa được triển khai.
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}