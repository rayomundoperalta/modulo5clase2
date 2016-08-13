// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp.reciclerview;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.facebook.drawee.view.SimpleDraweeView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import mx.peta.nasaapp.R;

public class NasaApodViewHolder_ViewBinding<T extends NasaApodViewHolder> implements Unbinder {
  protected T target;

  private View view2131493011;

  public NasaApodViewHolder_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.item_apod_image, "field 'itemApodImage' and method 'onViewClick'");
    target.itemApodImage = finder.castView(view, R.id.item_apod_image, "field 'itemApodImage'", SimpleDraweeView.class);
    view2131493011 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClick(p0);
      }
    });
    target.itemApodText = finder.findRequiredViewAsType(source, R.id.item_apod_text, "field 'itemApodText'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.itemApodImage = null;
    target.itemApodText = null;

    view2131493011.setOnClickListener(null);
    view2131493011 = null;

    this.target = null;
  }
}
