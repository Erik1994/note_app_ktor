package com.example.mynotes.ui.features.notes.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.databinding.ItemNoteBinding
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.ui.extensions.changeDrawableColor
import com.example.mynotes.ui.extensions.debounceClicks
import com.example.mynotes.ui.extensions.formatDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class NotesAdapter(
    private val coroutineScope: CoroutineScope?,
    private val appDispatchers: AppDispatchers
) : ListAdapter<NoteEntity, NotesAdapter.ViewHolder>(DiffCallBack()) {
    private val clickChannel: Channel<NoteEntity> = Channel()
    fun clicksFlow() = clickChannel.receiveAsFlow()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = currentList[position]
        holder.bind(currentNote)
    }

    @FlowPreview
    inner class ViewHolder(private val itemNoteBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemNoteBinding.root) {

        init {
            coroutineScope?.let {
                itemView.debounceClicks()
                    .onEach {
                        val currentNote = currentList[adapterPosition]
                        clickChannel.send(currentNote)
                    }
                    .flowOn(appDispatchers.mainDispatcherImmediate)
                    .launchIn(it)
            }
        }

        fun bind(noteEntity: NoteEntity) = with(itemNoteBinding) {
            tvTitle.text = noteEntity.title
            if (!noteEntity.isSynced) {
                ivSynced.setImageResource(R.drawable.ic_cross)
                tvSynced.text = root.context.getString(R.string.not_synced)
            } else {
                ivSynced.setImageResource(R.drawable.ic_check)
                tvSynced.text = root.context.getString(R.string.synced)
            }
            tvDate.text = noteEntity.date.formatDate("dd.MM.yy, HH:mm")
            val drawable =
                ResourcesCompat.getDrawable(root.resources, R.drawable.circle_shape, null)
            drawable?.let {
                viewNoteColor.background =
                    it.changeDrawableColor(Color.parseColor("#${noteEntity.color}"))
            }
        }
    }


    class DiffCallBack : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }
}