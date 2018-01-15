package info.dourok.android.demo;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class MainActivity extends Activity {
	DragContainer mDragContainer;
	// Create a string for the ImageView label
	private static final String IMAGEVIEW_TAG = "icon bitmap";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDragContainer = (DragContainer) findViewById(R.id.root);
		View drag = mDragContainer.findViewById(R.id.drag);
		View drop = mDragContainer.findViewById(R.id.drop);
		drag.setTag(IMAGEVIEW_TAG);

		// Sets a long click listener for the ImageView using an anonymous
		// listener object that
		// implements the OnLongClickListener interface
		drag.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				ClipData.Item item = new ClipData.Item((CharSequence) v
						.getTag());

				// Create a new ClipData using the tag as a label, the plain
				// text MIME type, and
				// the already-created item. This will create a new
				// ClipDescription object within the
				// ClipData, and set its MIME type entry to "text/plain"
				ClipData dragData = new ClipData((CharSequence) v.getTag(),
						new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
						item);

				// DragContainer has its own DragShadowBuilder
				return mDragContainer.startDragChild(v, dragData, // the data to
																	// be
																	// dragged
						null, // no need to use local data
						0 // flags (not currently used, set to 0)
						);

			}
		});

		drop.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				System.out.println("onDrag:" + event);
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_ENTERED:
					v.setBackgroundColor(Color.BLUE);
					return true;
				case DragEvent.ACTION_DRAG_EXITED:
				case DragEvent.ACTION_DRAG_ENDED:
					v.setBackgroundColor(Color.TRANSPARENT);
					return true;
				default:
					return true;
				}
			}
		});
	}

}
