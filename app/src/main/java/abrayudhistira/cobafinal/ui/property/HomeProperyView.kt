package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertiAdapter(private val propertiList: List<Properti>, private val context: Context) : RecyclerView.Adapter<PropertiAdapter.PropertiViewHolder>() {

    class PropertiViewHolder(layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        val txtNamaProperti: TextView = layout.findViewById(1)
        val txtLokasi: TextView = layout.findViewById(2)
        val txtHarga: TextView = layout.findViewById(3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertiViewHolder {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)

            addView(TextView(context).apply {
                id = 1
                textSize = 18f
                setTextColor(android.graphics.Color.BLACK)
                setTypeface(null, android.graphics.Typeface.BOLD)
            })
            addView(TextView(context).apply {
                id = 2
                textSize = 16f
                setTextColor(android.graphics.Color.BLACK)
            })
            addView(TextView(context).apply {
                id = 3
                textSize = 16f
                setTextColor(android.graphics.Color.BLACK)
            })
        }
        return PropertiViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PropertiViewHolder, position: Int) {
        val properti = propertiList[position]
        holder.txtNamaProperti.text = properti.nama_properti
        holder.txtLokasi.text = properti.lokasi
        holder.txtHarga.text = properti.harga.toString()
    }

    override fun getItemCount(): Int = propertiList.size
}