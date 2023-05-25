# Speedy Java

## Quick Start

```console
> javac Bench.java
> java Bench
```

## Lists

### Unordered List

This list is more or less the same as the `ArrayList`, except for when you remove elements. A general List implementation assmes that you want you want to keep the order of the elements in your list. Thus, when you remove an element in an `ArrayList`, all elements after it have to be shifted one to the left. My `UnorderedList` does not make that assumption, thus we can increase perfomance significantly. The way the `UnorderedList` removes an element is by switching it with the last element in the underlying array and decreasing the apparent length of the list. This simple change increases perfomance by an order of magnitude.
