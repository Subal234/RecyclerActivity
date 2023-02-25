package com.androidstudio.recycleractivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudio.recycleractivity.databinding.ActivityMainBinding
import com.androidstudio.recycleractivity.databinding.ItemAddDialogBinding
import com.androidstudio.recycleractivity.databinding.ItemUpdateDialogBinding


class MainActivity : AppCompatActivity(),RecyclerInterface {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: RecyclerClass
    lateinit var LinearLayoutManager: LinearLayoutManager
    var array: ArrayList<StudentInfo> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerClass(array, this)
        LinearLayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = LinearLayoutManager
        binding.recycler.adapter = adapter
        binding?.fabAdd?.setOnClickListener {
            var dialogBinding = ItemAddDialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etentername.text.toString().isNullOrEmpty())
                    dialogBinding.etentername.setError("Enter Name")
                else if (dialogBinding.etenterphone.text.toString().isNullOrEmpty())
                    dialogBinding.etenterphone.setError("Enter Phone")
                else if (dialogBinding.etenteraddress.text.toString().isNullOrEmpty())
                    dialogBinding.etenteraddress.setError("Enter Address")
                else {
                    array.add(
                        StudentInfo(
                            dialogBinding.etentername.text.toString(),
                            dialogBinding.etenterphone.text.toString(),
                            dialogBinding.etenteraddress.text.toString()
                        )
                    )
                    dialog.dismiss()
                }

            }
            dialog.show()
        }


    }

    override fun onUpdateClick(studentInfo: StudentInfo, position: Int) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Update Or Delete")
        alertDialog.setMessage(resources.getString(R.string.message))
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Update") { _, _ ->
            var diaBinding = ItemUpdateDialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(diaBinding.root)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            diaBinding.etentername.setText(studentInfo.name)
            diaBinding.etenterphone.setText(studentInfo.phone)
            diaBinding.etenteraddress.setText(studentInfo.address)

            diaBinding.btnupdate.setOnClickListener {
                if (diaBinding.etentername.text.toString().isNullOrEmpty())
                    diaBinding.etentername.setError("Enter Name")
                else if (diaBinding.etenterphone.text.toString().isNullOrEmpty())
                    diaBinding.etenterphone.setError("Enter Phone")
                else if (diaBinding.etenteraddress.text.toString().isNullOrEmpty())
                    diaBinding.etenteraddress.setError("Enter Address")
                else {
                    array[position] = (StudentInfo(
                        diaBinding.etentername.text.toString(),
                        diaBinding.etenterphone.text.toString(),
                        diaBinding.etenteraddress.text.toString()
                    ))
                    dialog.dismiss()
                    adapter.notifyDataSetChanged()
                }

            }
            dialog.show()
        }
        alertDialog.setNegativeButton("Delete") { _, _ ->
            array.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        alertDialog.setNeutralButton("Cancel") { _, _ ->
            alertDialog.setCancelable(true)
        }
        alertDialog.show()
    }
}


