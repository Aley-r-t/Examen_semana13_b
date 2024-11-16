package com.contreras.rodrigo.examen_semana13_b

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contreras.rodrigo.examen_semana13_b.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val context: Context,
    private val teacherList: List<TeacherModel>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: TeacherModel) {
            binding.tvName.text = "${teacher.name} ${teacher.last_name}"
            binding.tvPhone.text = teacher.phone
            binding.tvEmail.text = teacher.email

            Glide.with(context)
                .load(teacher.imageUrl)
                .into(binding.ivProfileImage)

            binding.root.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${teacher.phone}")
                }
                context.startActivity(callIntent)
            }

            binding.root.setOnLongClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${teacher.email}")
                }
                try {
                    context.startActivity(emailIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, "No hay aplicación de correo instalada", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teacherList[position])
    }

    override fun getItemCount(): Int = teacherList.size
}