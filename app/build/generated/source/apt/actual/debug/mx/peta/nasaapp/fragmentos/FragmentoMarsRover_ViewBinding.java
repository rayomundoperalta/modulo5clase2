// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp.fragmentos;

import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import mx.peta.nasaapp.R;

public class FragmentoMarsRover_ViewBinding<T extends FragmentoMarsRover> implements Unbinder {
  protected T target;

  public FragmentoMarsRover_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.marsRoverListingRecycler = finder.findRequiredViewAsType(source, R.id.recyclerView, "field 'marsRoverListingRecycler'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.marsRoverListingRecycler = null;

    this.target = null;
  }
}
