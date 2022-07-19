package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй
 дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

	Entry<String> root;
	private ArrayDeque<Entry<String>> branches = new ArrayDeque<>();
	private Set<Entry<String>> removeList = new TreeSet<>(Comparator.comparing(Entry::toString));

	public CustomTree() {
		this.root = new Entry<>("0");
		root.parent = root;
		branches.add(root.parent);
	}

	public CustomTree(Entry<String> root) {
		this.root = new Entry<>("0");
		root.parent = root;
		branches.add(root.parent);
	}

	public boolean remove(Object o) {

		if(o == null) {
			return false;
		}
		if(!(o instanceof String)) {
			throw new UnsupportedOperationException();
		}

		String elementName = o.toString();


		for(Entry<String> entry : branches
		) {
			if(elementName.equals(entry.elementName)) {
				if(entry.leftChild == null & entry.rightChild == null) {
					if(entry.parent.leftChild.equals(entry)) {
						removeList.add(entry);

					} else if(entry.parent.rightChild.equals(entry)) {
						removeList.add(entry);
					}
				} else {
					removeList.add(entry);
					if(entry.leftChild != null){
						removeList.add(entry.leftChild);
						remove(entry.leftChild.elementName);
					}
					if(entry.rightChild != null) {
						removeList.add(entry.rightChild);
						remove(entry.rightChild.elementName);
					}
					break;
				}
			}
		}


		branches.removeAll(removeList);
		removeList.clear();

		for(Entry<String> ent : branches
		) {
			if(ent.leftChild == null & !ent.availableToAddLeftChildren) {
				ent.availableToAddLeftChildren = true;
			}
			if(ent.rightChild == null & !ent.availableToAddRightChildren) {
				ent.availableToAddRightChildren = true;
			}
		}
		return true;
	}

	@Override
	public boolean add(String elementName) {

		Entry<String> newBranch = new Entry<String>(elementName);
		for(Entry<String> entry : branches) {
			if(entry.isAvailableToAddChildren()) {
				if(entry.availableToAddLeftChildren) {
					entry.leftChild = newBranch;
					entry.availableToAddLeftChildren = false;
					newBranch.parent = entry;
					branches.add(newBranch);
					return true;

				} else if(entry.availableToAddRightChildren) {
					entry.rightChild = newBranch;
					entry.availableToAddRightChildren = false;
					newBranch.parent = entry;
					branches.add(newBranch);
					return true;
				}
			}
		}

		List<Entry<String>> deadEndList = new ArrayList<>(branches);

		for(int i = deadEndList.size()/2; i < deadEndList.size(); i++) {
			for(Entry<String> entry: branches
			    ) {
				if(entry.equals(deadEndList.get(i))){
					entry.availableToAddLeftChildren = true;
					entry.availableToAddRightChildren = true;
				}
			}
		}

		add(elementName);
		return true;
	}

	public String getParent(String s) {

		for(Entry<String> entry : branches
		) {
			if(entry.elementName.equals(s)) {
				return entry.parent.elementName;
			}
		}
		return "null";
	}

	@Override
	public void add(int index, String element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String set(int index, String element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return branches.size() - 1;
	}

	static class Entry<T> implements Serializable {
		String elementName;
		boolean availableToAddLeftChildren;
		boolean availableToAddRightChildren;
		Entry<T> parent;
		Entry<T> leftChild;

		Entry<T> rightChild;

		public Entry(String elementName) {
			this.elementName = elementName;
			availableToAddLeftChildren = true;
			availableToAddRightChildren = true;
		}

		public boolean isAvailableToAddChildren() {
			return availableToAddLeftChildren | availableToAddRightChildren;
		}
	}
}
