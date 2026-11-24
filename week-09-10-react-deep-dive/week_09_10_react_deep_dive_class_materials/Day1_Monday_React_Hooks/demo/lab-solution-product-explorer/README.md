# Day 1 Lab Solution — Product Explorer (pair programming)

- **Partner A:** `ProductSearch` filters the list in real time (no API call — derived from state).
- **Partner B:** `Pagination` shows 5 products per page with Previous/Next.
- **Together:** sort dropdown (name / price).

Key idea: only `term`, `sort`, and `page` are state. The filtered, sorted and paged lists are
*derived* during render (memoized with `useMemo`).

```bash
npm install && npm run dev
```
